package com.test.web.board.model.vo;

import java.sql.Date;

public class Board implements java.io.Serializable {

	private static final long serialVersionUID = 4790505937786551193L;
	
	private int board_num;
	private String board_writer;
	private String board_title;
	private String board_content;
	private String board_original_filename;
	private String board_rename_filename;
	private int board_ref;
	private int board_reply_ref;
	private int board_lev;
	private int board_reply_seq;
	private int board_readcount;
	private Date board_date;
	
	public Board() {
		super();
	}

	public Board(int board_num, String board_writer, String board_title, String board_content,
			String board_original_filename, String board_rename_filename, int board_ref, int board_reply_ref,
			int board_lev, int board_reply_seq, int board_readcount, Date board_date) {
		super();
		this.board_num = board_num;
		this.board_writer = board_writer;
		this.board_title = board_title;
		this.board_content = board_content;
		this.board_original_filename = board_original_filename;
		this.board_rename_filename = board_rename_filename;
		this.board_ref = board_ref;
		this.board_reply_ref = board_reply_ref;
		this.board_lev = board_lev;
		this.board_reply_seq = board_reply_seq;
		this.board_readcount = board_readcount;
		this.board_date = board_date;
	}

	public int getBoard_num() {
		return board_num;
	}

	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}

	public String getBoard_writer() {
		return board_writer;
	}

	public void setBoard_writer(String board_writer) {
		this.board_writer = board_writer;
	}

	public String getBoard_title() {
		return board_title;
	}

	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}

	public String getBoard_content() {
		return board_content;
	}

	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}

	public String getBoard_original_filename() {
		return board_original_filename;
	}

	public void setBoard_original_filename(String board_original_filename) {
		this.board_original_filename = board_original_filename;
	}

	public String getBoard_rename_filename() {
		return board_rename_filename;
	}

	public void setBoard_rename_filename(String board_rename_filename) {
		this.board_rename_filename = board_rename_filename;
	}

	public int getBoard_ref() {
		return board_ref;
	}

	public void setBoard_ref(int board_ref) {
		this.board_ref = board_ref;
	}

	public int getBoard_reply_ref() {
		return board_reply_ref;
	}

	public void setBoard_reply_ref(int board_reply_ref) {
		this.board_reply_ref = board_reply_ref;
	}

	public int getBoard_lev() {
		return board_lev;
	}

	public void setBoard_lev(int board_lev) {
		this.board_lev = board_lev;
	}

	public int getBoard_reply_seq() {
		return board_reply_seq;
	}

	public void setBoard_reply_seq(int board_reply_seq) {
		this.board_reply_seq = board_reply_seq;
	}

	public int getBoard_readcount() {
		return board_readcount;
	}

	public void setBoard_readcount(int board_readcount) {
		this.board_readcount = board_readcount;
	}

	public Date getBoard_date() {
		return board_date;
	}

	public void setBoard_date(Date board_date) {
		this.board_date = board_date;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Board [board_num=" + board_num + ", board_writer=" + board_writer + ", board_title=" + board_title
				+ ", board_content=" + board_content + ", board_original_filename=" + board_original_filename
				+ ", board_rename_filename=" + board_rename_filename + ", board_ref=" + board_ref + ", board_reply_ref="
				+ board_reply_ref + ", board_lev=" + board_lev + ", board_reply_seq=" + board_reply_seq
				+ ", board_readcount=" + board_readcount + ", board_date=" + board_date + "]";
	}
	
	

}
