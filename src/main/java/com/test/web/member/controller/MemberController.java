package com.test.web.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.test.web.common.SearchDate;
import com.test.web.member.model.service.MemberService;
import com.test.web.member.model.vo.Member;

@Controller
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private MemberService memberService;
	
	@Bean
	   BCryptPasswordEncoder passwordEncoder() {

	       return new BCryptPasswordEncoder();
	   }
	
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	
		
	@RequestMapping("loginPage.do")
	public String moveLoginPage() {
		return "member/loginPage2";
	}
	
	@RequestMapping("enrollPage.do")
	public String moveEnrollPage() {
		return "member/enrollPage2";
	}
	
	@RequestMapping("error.do")
	public String moveErrorPage() {
		return "common/error";
	}
	
	
	//로그인처리용
//	@RequestMapping(value="login.do", method=RequestMethod.POST)
//	public String loginMethod(HttpServletRequest request,
//			HttpServletResponse response, Model model) {
//		
//		//서비스로 전달할 멤버객체생성
//		Member member = new Member();
//		member.setUserid(request.getParameter("userid"));
//		member.setUserpwd(request.getParameter("userpwd"));
//		
//		Member loginMember = memberService.selectLogin(member);
//		String viewName = null;
//		if(loginMember != null) {
//			//로그인상태관리방법 : 상태관리 매커니즘 이라고함
//			//세션 쿠키 url재작성 3가지방법있음
//			HttpSession session = request.getSession();
//			//getSession() == getSession(true)
//			//세션객체가 없으면 자동으로 생성시킴
//			//세션객체가 있으면 해당세션의 정보를 리턴받음
//			session.setAttribute("loginMember", loginMember);
//			viewName = "common/main";
//		}else { //로그인 실패시
//			model.addAttribute("message", "로그인 실패 : 아이디나 비번 확인하시오");
//			viewName = "common/error";
//		}
//		
//		return viewName;
//		
//	}
	
	@RequestMapping(value="login.do", method=RequestMethod.POST)
	public String loginMethod(Member member, HttpSession session, SessionStatus status, Model model) {
		logger.info("login.do : " + member);
		
		//암호화 처리된 패스워드 일치 조회는 select 해온 값으로 비교해야함
		//회원아이디로 회원정보를 먼조조회
		Member loginMember = memberService.selectMember(member.getUserid());
		
		//암호화된패스워드와 전달되패스워드가 일치하는지 여부확인
		//matches(일반글자패스워드, 암호화된패스워드)  이런형식으로비교
		if(loginMember != null && bcryptPasswordEncoder.matches(member.getUserpwd(), loginMember.getUserpwd())
				&& loginMember.getLogin_ok().equals("Y")) {
			
			//세션 객체 생성 > 세션 안에 회원정보 저장
			session.setAttribute("loginMember", loginMember);
			status.setComplete(); //로그인 요청 성공, 브라우저쪽엔 200이 전송됨
			return "common/main2";
			
		}else {
			model.addAttribute("message", "로그인실패 : 아이디/암호를 확인하시오");
			return "common/error";
		}
		
		
	}
	
	
	//로그아웃 처리용
	@RequestMapping("logout.do")
	public String logoutMethod(HttpServletRequest request, Model model) {
		//로그인할때 생성한 세션객체를 없애는게 로그아웃
		HttpSession session = request.getSession(false);
		//세션객체가 있으면 리턴받고
		//세션개체가 없으면 null리턴(자동생성이없다는얘기)
		if(session != null) { //세션이널아니면로그인상태=세션이있다
			session.invalidate(); //해당 세션객체없앰
			return "common/main2";
		}else {
			model.addAttribute("message", "로그인세션이 존재하지않습니다");
			return "common/error";
		}
	}
		
	
	//회원가입처리용
	@RequestMapping(value="enroll.do", method=RequestMethod.POST)
	public String memberInsertMethod(Member member, Model model) {
		//메소드 매개변수에 vo에 대한 객체를 작성하면
		//뷰 form 태그 input의 name과 vo의 필드명이 같으면
		//자동 값이 꺼내져서 객체에 옮겨기록저장됨
		//이걸 커맨드객체라고한다
		logger.info("enroll.do : " + member);
		
		//패스워드 암호화처리 추가
		member.setUserpwd(bcryptPasswordEncoder.encode(member.getUserpwd())); //이게끝
		logger.info("after encode : "+member);
		
		if(memberService.insertMember(member)>0) {
			return "common/main2";
		}else {
			model.addAttribute("message", "회원가입실패");
			return "common/error";
		}
	}
	
	//프로필조회
	@RequestMapping("myinfo.do")
	public ModelAndView myInfoMethod(@RequestParam("userid") String userid, ModelAndView mv) {
		Member member = memberService.selectMember(userid);
		
		if(member != null) { //조회가 성공했다면
			mv.addObject("member", member);
			mv.setViewName("member/myInfoPage");			
		}else { //조회실패했다면
			mv.addObject("message", userid + " : 회원정보조회 실패");
			mv.setViewName("common/error");
		}
		
		return mv;
		
	}
	
	@RequestMapping(value="mupdate.do", method=RequestMethod.POST)
	public String memberUpdateMethod(Member member, Model model, @RequestParam("origin_userpwd") String originUserpwd) {
		logger.info("mupdate.do : " + member);
		logger.info("origin_userpwd : "+originUserpwd);
		
		//새암호전송왔다면
		String userpwd = member.getUserpwd().trim();
		if(userpwd !=null && userpwd.length() > 0 ) {
			//기존암와다른값
			if(!bcryptPasswordEncoder.matches(userpwd, originUserpwd)) {
				//멤버에새로운암호저장 : 암호화처리해서 넣어라
				member.setUserpwd(bcryptPasswordEncoder.encode(userpwd));
				//암호화된건 이퀄스못씀 matches사용
			}
			
		}else {
			//새암호없다면 기존암호와같다면 원래암호기록
			member.setUserpwd(originUserpwd);
		}
		
		logger.info("after : " + member); //암호가비어있지않은지확인
		
		if(memberService.updateMember(member)>0) {
			//수정성공했으면 컨트롤러 메소드직접호출
			//내정보보기페이지에 수정된회원정보를 다시조회해와서 출력되게처리
			return "redirect:myinfo.do?userid="+member.getUserid();
		}else {
			model.addAttribute("message", "회원정보 수정실패");
			return "common/error";
			
		}
		
	}
	
	
	//회원 탈퇴처리 (삭제처리) (업데이트로도가능)
	@RequestMapping("mdel.do")
	public String memberDeleteMethod(@RequestParam("userid") String userid, Model model) {
		if(memberService.deleteMember(userid)>0) {
			return "redirect:logout.do";
		}else {
			model.addAttribute("message", userid + "회원삭제실패");
			return "common/error";
		}
		
	}
	
	
	
	//아이디중복처리
	@RequestMapping(value="idchk.do", method=RequestMethod.POST)
	public void dupIdCheckMethod(@RequestParam("userid") String userid, 
			HttpServletResponse response) throws IOException {
		
		int idcount = memberService.selectDupCheckId(userid);
		
		String returnValue=null;
		if(idcount == 0) {
			returnValue = "ok";  //0리턴아이디없음
		}else {
			returnValue = "dup";  //1리턴아이디있음
		}
		
		//response를 이용해서 클라이언트로 출력스트림만들고 값보내기
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.append(returnValue);
		out.flush();
		out.close();
	}
	
	
	//관리자 회원관리기능 처리용
	@RequestMapping("mlist.do")
	public String memberListViewMethod(Model model) {
		ArrayList<Member> list = memberService.selectList();
		
		if(list.size()>0) {
			model.addAttribute("list", list);
			return "member/memberListView";
		}else {
			model.addAttribute("message", "회원 목록이 존재하지 않는다");
			return "common/error";			
		}		
	}
	
	//로그인 제한/가능
	@RequestMapping("loginok.do")
	public String changeLoginOKmethod(Member member, Model model) {
		logger.info("loginok.do : "+member.getUserid()+", "+member.getLogin_ok());
		
		if(memberService.updateLoginOk(member)>0) {
			return "redirect:mlist.do";
		}else {
			model.addAttribute("message", "로그인 제한/허용 처리 오류발생");
			return "common/error";
		}
		
	}
		
	//회원 검색 기능 처리용
	@RequestMapping(value="msearch.do", method=RequestMethod.POST)
	public String memberSearchMethod(HttpServletRequest request, Model model) {
		String action = request.getParameter("action");
		String keyword = null, beginDate=null, endDate=null;
		
		if(action.equals("enrolldate")) {
			beginDate = request.getParameter("begin");
			endDate = request.getParameter("end");
			
		}else {
			keyword=request.getParameter("keyword");
		}
		
		//서비스 메소드 리턴값 받을 리스트 준비
		ArrayList<Member> list = null;
		
		switch(action) {
		case "id": list = memberService.selectSearchUserid(keyword);
		break;
		case "gender": list = memberService.selectSearchGender(keyword);
		break;
		case "age": list = memberService.selectSearchAge(Integer.parseInt(keyword));
		break;
		case "enrolldate": list = memberService.selectSearchEnrollDate(
				new SearchDate(Date.valueOf(beginDate), Date.valueOf(endDate)));
		break;
		case "loginok": list = memberService.selectSearchLoginOk(keyword);
		break;		
		}
		
		if(list.size()>0) {
			model.addAttribute("list", list);
			return "member/memberListView";
		}else {
			model.addAttribute("message", action+"검색에 대한 "+keyword+"결과가 없다");
			return "common/error";
		}
		
	}	
		
		
	
}




