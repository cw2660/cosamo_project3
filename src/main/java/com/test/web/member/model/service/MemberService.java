package com.test.web.member.model.service;

import java.util.ArrayList;

import com.test.web.common.SearchDate;
import com.test.web.member.model.vo.Member;

public interface MemberService {
	Member selectLogin(Member member);
	int insertMember(Member member);
	int selectDupCheckId(String userid); //회원가입시 아이디중복체크메소드
	int updateMember(Member member);
	int deleteMember(String userid);
	ArrayList<Member> selectList();
	Member selectMember(String userid);
	int updateLoginOk(Member member);
	ArrayList<Member> selectSearchUserid(String keyword);
	ArrayList<Member> selectSearchGender(String keyword);
	ArrayList<Member> selectSearchAge(int age);
	ArrayList<Member> selectSearchEnrollDate(SearchDate searchDate);
	ArrayList<Member> selectSearchLoginOk(String keyword);
}
