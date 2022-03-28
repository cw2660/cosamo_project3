package com.test.web.common;

import java.sql.Date;

import org.springframework.stereotype.Component;

//일반클래스는 xml에 등록할때 컴포넌트 어노테이션 사용
//자동등록하는목적은 스프링의 DI 기능을 사용하기위해
//@Component("searchDate")
public class SearchDate implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5880625008368618729L;
	
	//검색에 사용할 범위
	private Date begin;
	private Date end;
	
	public SearchDate() {
	}

	public SearchDate(Date begin, Date end) {
		super();
		this.begin = begin;
		this.end = end;
	}

	public Date getBegin() {
		return begin;
	}

	public void setBegin(Date begin) {
		this.begin = begin;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "SearchDate [begin=" + begin + ", end=" + end + "]";
	}
		

}
