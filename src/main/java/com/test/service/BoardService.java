package com.test.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.test.dao.BoardDao;
import com.test.dto.BoardDto;
import com.test.dto.Criteria;
import com.test.dto.PageDto;

public class BoardService {
	
	BoardDao dao =BoardDao.getInstance();
	 
	//싱글톤
	private static BoardService instance;
	public static BoardService getInstance() {
		if(instance==null)
			instance=new BoardService();
		return instance;
	}
	private BoardService() {};
	//싱글톤
	
	//모든 게시물 가져오기
//	public boolean GetBoardList(HttpServletRequest request) {
//		List<BoardDto> list = dao.SelectAll();
//		if(list!=null) {
//			request.setAttribute("list", list);
//			return true;
//		}
//		return false;
//	}
	
	
	//모든 게시물 가져오기
	public boolean GetBoardList(Criteria criteria, HttpServletRequest request) {
		
		//전체게시물 건수 받기
		int totalcount = dao.getAmount();
		
		//PageDto 만들기
		PageDto pagedto = new PageDto(totalcount,criteria);
		
		//시작 게시물 번호 구하기(수정)
		int startno = criteria.getPageno()*criteria.getAmount()-criteria.getAmount();
		
		
		List<BoardDto> list = dao.SelectAll(startno,criteria.getAmount());
		if(list!=null) {
			request.setAttribute("list", list);
			request.setAttribute("pagedto", pagedto);
			return true;
		}
		return false;
	}
	
	
}
