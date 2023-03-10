package com.test.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.dto.BoardDto;
import com.test.service.BoardService;

public class BoardPostController implements SubController {

	private static String msg;
	private BoardService service = BoardService.getInstance();	 
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		
		try {
			
			//0 Get구별
			String method=req.getMethod();
			if(method.equals("GET")) {

				System.out.println("[BPC] 요청 METHOD : " + method);
				req.getRequestDispatcher("/WEB-INF/view/board/post.jsp").forward(req, resp);
				return ;
			}
			
			
			//1 파라미터 받기
			Map<String,String[]> params=req.getParameterMap();
			System.out.println("PARAMS : " + params);
		
			
			
			//2 Validation
			boolean isvalid=isValid(params);
			if(!isvalid) {
				//유효성 체크 오류 발생시 전달할 메시지 + 이동될 경로			
				req.setAttribute("msg", msg);
				req.getRequestDispatcher("/WEB-INF/view/board/post.jsp").forward(req, resp);
				
				
				
				return ;
			}
			
			//3 Service		
			
			BoardDto dto = new BoardDto();
//			dto.setEmail(params.get("email")[0]);
//			dto.setTitle(params.get("title")[0]);
//			dto.setContent(params.get("content")[0]);
			
			boolean result=service.PostBoard(dto,req);

			//4 View
			if(result) {		
				req.getRequestDispatcher("/WEB-INF/view/board/list.jsp").forward(req, resp);
			}
			
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}
	
	
	private boolean isValid(Map<String, String[]> params) {
		//msg="유효성 체크 오류";
		return true;
	}

}
