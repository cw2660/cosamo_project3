package com.test.web.board.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.web.board.model.service.BoardService;
import com.test.web.board.model.vo.Board;

@Controller
public class BoardController {
private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value="btop3.do", method=RequestMethod.POST)
	@ResponseBody
	public String boardReadTop3Method(HttpServletResponse response) throws UnsupportedEncodingException {
		//조회수많은 게시원글 3개조회 해오기
		ArrayList<Board> list = boardService.selectTop3();
		
		//전소용 json객체 준비
		JSONObject sendJson = new JSONObject();
		//list옮길 json배열 준비
		JSONArray jarr = new JSONArray();
		
		//list를 jarr로 옮기기(복사)
		for(Board board : list) {
			//notice 필드값 저장용 json객체 만들고
			JSONObject job = new JSONObject();
			
			//json객체저장할때 put, 대부분map구조니까 json이
			job.put("board_num", board.getBoard_num());
			job.put("board_title", URLEncoder.encode(board.getBoard_title(), "utf-8"));
			//한글은 인코딩 디코딩해야된다 여기서인코딩 저기받는뷰?에서 디코딩
			job.put("board_readcount", board.getBoard_readcount());  //스트링으로안바꾸면null로됨 날짜만
			
			jarr.add(job); //배열에저장
		}
		//전송용객체에 jarr을 담음
		sendJson.put("list", jarr);
		
		return sendJson.toJSONString(); //제이슨객체를 제이슨문자열형태로 보냄
		//뷰리졸버에게 리턴됨
		
	}
}
