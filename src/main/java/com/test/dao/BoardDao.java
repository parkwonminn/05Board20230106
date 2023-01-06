package com.test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.test.dto.BoardDto;
import com.test.dto.MemberDto;

public class BoardDao {
	
	//DataSource
	private DataSource ds;
	
	//싱글톤패턴
	private static BoardDao instance;
	public static BoardDao getInstance() {
		if(instance==null)
			instance = new BoardDao();
		return instance;
	}
	
	
	private BoardDao() {
		//DB관련 코드 적용(DBCP / HikariDb / Mabatis / JPA ...)
		try {
			//1. JNDI 객체 생성
			InitialContext ic= new InitialContext();
			//2. DataSource 자원 찾기
			ds = (DataSource) ic.lookup("java:comp/env/jdbc/mysql");
			
			System.out.println("DS : " + ds);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	//싱글톤패턴
	
	//SELECTAll
	public List<BoardDto> SelectAll() {
		
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<BoardDto> list = new ArrayList();
		BoardDto dto = null;
		

		try {
			conn = ds.getConnection();
			pstmt=conn.prepareStatement("select * from tbl_board");
			
			rs=pstmt.executeQuery();
			if(rs!=null)
			{
				while(rs.next())
				{
					dto = new BoardDto();
					dto.setNo( rs.getInt(1)+"" );
					dto.setEmail(rs.getString(2));
					dto.setTitle(rs.getString(3));
					dto.setContent(rs.getString(4));
					dto.setRegdate(rs.getString(5));
					dto.setCount(rs.getInt(6)+"");
					dto.setDirpath(rs.getString(7));
					dto.setFilename(rs.getString(8));
					dto.setFilesize(rs.getString(9));
					list.add(dto);
				}
				
			}	
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		
		}finally {
			try {rs.close();}catch(Exception e) {}
			try {pstmt.close();}catch(Exception e) {}
			try {conn.close();}catch(Exception e) {}
		}
		
		return list;
	}
	
	
	
	
	
	

	
}
