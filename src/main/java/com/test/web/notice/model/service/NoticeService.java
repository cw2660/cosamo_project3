package com.test.web.notice.model.service;

import java.util.ArrayList;

import com.test.web.common.SearchDate;
import com.test.web.notice.model.vo.Notice;

public interface NoticeService {
	ArrayList<Notice> selectAll();
	Notice selectNotice(int noticeno);
	int inserNotice(Notice notice);
	int updateNotice(Notice notice);
	int deleteNotice(int noticeno);
	ArrayList<Notice> selectNewTop3();
	ArrayList<Notice> selectSearchTitle(String keyword);
	ArrayList<Notice> selectSearchWriter(String keyword);
	ArrayList<Notice> selectSearchDate(SearchDate date);
}
