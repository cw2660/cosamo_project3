package com.test.web.member.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.web.common.SearchDate;
import com.test.web.member.model.dao.MemberDao;
import com.test.web.member.model.vo.Member;

@Service("memberService")
public class MemberServiceImpl implements MemberService {

	@Autowired //자동의존성주입 (내부에서 자동으로 객체생성)
	private MemberDao memberDao;
	
	//뷰페이지 이동 처리용 메소드
	@RequestMapping("loginPage.do")
	public String moveLoginPage() {
		return "member/loginPage";
	}

	@Override
	public Member selectLogin(Member member) {
		return memberDao.selectLogin(member);
	}

	@Override
	public int insertMember(Member member) {
		return memberDao.insertMember(member);
	}

	@Override
	public int selectDupCheckId(String userid) {
		return memberDao.selectDupCheckId(userid);
	}

	@Override
	public int updateMember(Member member) {
		return memberDao.updateMember(member);
	}

	@Override
	public int deleteMember(String userid) {
		return memberDao.deleteMember(userid);
	}

	@Override
	public ArrayList<Member> selectList() {
		return memberDao.selectList();
	}

	@Override
	public Member selectMember(String userid) {
		return memberDao.selectMember(userid);
	}

	@Override
	public int updateLoginOk(Member member) {
		return memberDao.updateLoginOk(member);
	}

	@Override
	public ArrayList<Member> selectSearchUserid(String keyword) {
		return memberDao.selectSearchUserid(keyword);
	}

	@Override
	public ArrayList<Member> selectSearchGender(String keyword) {
		return memberDao.selectSearchGender(keyword);
	}

	@Override
	public ArrayList<Member> selectSearchAge(int age) {
		return memberDao.selectSearchAge(age);
	}

	@Override
	public ArrayList<Member> selectSearchEnrollDate(SearchDate searchDate) {
		return memberDao.selectSearchEnrollDate(searchDate);
	}

	@Override
	public ArrayList<Member> selectSearchLoginOk(String keyword) {
		return memberDao.selectSearchLoginOk(keyword);
	}

}
