package com.zeratul.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zeratul.service.UserService;

public class UserActiveServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7489385367425084603L;



	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String activeCode = request.getParameter("activeCode");
		
		UserService service=new UserService();
		try {
			
			service.activeUser(activeCode);
			
			response.sendRedirect(request.getContextPath()+"/login.jsp");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		
	}
	
	
	
	

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}