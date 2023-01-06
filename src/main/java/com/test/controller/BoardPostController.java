package com.test.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardPostController implements SubController {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		
		try {
			
			req.getRequestDispatcher("/WEB-INF/view/board/post.jsp").forward(req, resp);
			
			
		} catch (Exception e) {
			
		}
		

	}

}
