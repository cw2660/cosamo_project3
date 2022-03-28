package com.test.web.board.model.service;

import java.util.ArrayList;

import com.test.web.board.model.vo.Board;
import com.test.web.common.Paging;

public interface BoardService {
	ArrayList<Board> selectTop3();
	int selectListCount(); // 총게시글 갯수 조회용 :페이지수계산위해
	ArrayList<Board> selectList(Paging page); //한 페이지 출력할 게시글 조회용
	Board selectBoard(int board_num); //해당번호에대한 게시글상세 조회용
	int updateAddReadcount(int board_num); //상세보기시 조회수1증가처리
	int insertOriginBoard(Board board); //원글 등록용
	int insertReply(Board reply); //댓글 등록용
	int updateReplySeq(Board reply); //댓글 등록시 기존 댓글순번 1증가처리
	int updateOrigin(Board board); //원글수정용
	int updateReply(Board reply); //댓글수정용
	int deleteBoard(Board board); //게시글삭제용 (내용댓글전부삭제)
}
