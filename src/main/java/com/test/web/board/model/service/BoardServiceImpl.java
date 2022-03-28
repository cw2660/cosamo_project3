package com.test.web.board.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.web.board.model.dao.BoardDao;
import com.test.web.board.model.vo.Board;
import com.test.web.common.Paging;

@Service("boardService")
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardDao boardDao;
	
	@Override
	public ArrayList<Board> selectTop3() {
		return boardDao.selectTop3();
	}

	@Override
	public int selectListCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Board> selectList(Paging page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Board selectBoard(int board_num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateAddReadcount(int board_num) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertOriginBoard(Board board) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertReply(Board reply) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateReplySeq(Board reply) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateOrigin(Board board) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateReply(Board reply) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteBoard(Board board) {
		// TODO Auto-generated method stub
		return 0;
	}
}
