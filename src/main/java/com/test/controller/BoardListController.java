package com.test.controller;

import java.net.URLEncoder;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.dto.MemberDto;
import com.test.service.BoardService;

public class BoardListController implements SubController {

	private static String msg;
	private BoardService service = BoardService.getInstance();
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		
		//System.out.println("[MJC] Start-");
		try 
		{
			
			//1 파라미터 받기
			Map<String,String[]> params=req.getParameterMap();

			
			//2 Validation
			boolean isvalid=isValid(params);
			if(!isvalid) {
				//유효성 체크 오류 발생시 전달할 메시지 + 이동될 경로			
				req.setAttribute("msg", msg);
				req.getRequestDispatcher("/WEB-INF/view/main.jsp").forward(req, resp);
				return ;
			}
			
			//3 Service
			boolean result=service.GetBoardList(req);

			//4 View
			if(result) {		
				req.getRequestDispatcher("/WEB-INF/view/board/list.jsp").forward(req, resp);
			}
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	private boolean isValid(Map<String, String[]> params) {
		return true;
	}

	
}
