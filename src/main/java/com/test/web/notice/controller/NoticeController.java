package com.test.web.notice.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.web.notice.model.service.NoticeService;
import com.test.web.notice.model.vo.Notice;

@Controller
public class NoticeController {
	private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);
	
	@Autowired
	private NoticeService noticeService;
	
	@RequestMapping(value="ntop3.do", method=RequestMethod.POST)
	@ResponseBody
	public String noticeNewTop3Method(HttpServletResponse response) throws UnsupportedEncodingException {
		//최근등록공지글 3개조회먼저해오기
		ArrayList<Notice> list = noticeService.selectNewTop3();
		
		//전송용 json객체 준비
		JSONObject sendJson = new JSONObject();
		//list옮길 json배열 준비
		JSONArray jarr = new JSONArray();
		
		//list를 jarr로 옮기기(복사)
		for(Notice notice : list) {
			//notice 필드값 저장용 json객체 만들고
			JSONObject job = new JSONObject();
			
			//json객체저장할때 put, 대부분map구조니까 json이
			job.put("noticeno", notice.getNoticeno());
			job.put("noticetitle", URLEncoder.encode(notice.getNoticetitle(), "utf-8"));
			//한글은 인코딩 디코딩해야된다 여기서인코딩 저기받는뷰?에서 디코딩
			job.put("noticedate", notice.getNoticedate().toString());  //스트링으로안바꾸면null로됨 날짜만
			
			jarr.add(job); //배열에저장
		}
		//전송용객체에 jarr을 담음
		sendJson.put("list", jarr);
		
		return sendJson.toJSONString(); //제이슨객체를 제이슨문자열형태로 보냄
		//뷰리졸버에게 리턴됨
		
	}
	
	
	
	//공지사항 전체글 목록 조회
		@RequestMapping("nlist.do")
		public String noticeListMethod(Model model) {
			ArrayList<Notice> list = noticeService.selectAll();
			
			if(list.size()>0) {
				model.addAttribute("list", list);
				return "notice/noticeListView";
				
			}else {
				model.addAttribute("message", "등록된 공지가 없습니다");
				return "common/error";
			}
			
		}
	
}





