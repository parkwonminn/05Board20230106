package com.test.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.test.dao.BoardDao;
import com.test.dto.BoardDto;

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
	public boolean GetBoardList(HttpServletRequest request) {
		List<BoardDto> list = dao.SelectAll();
		if(list!=null) {
			request.setAttribute("list", list);
			return true;
		}
		return false;
	}
	
	
	
	
}
