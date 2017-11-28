package com.zeratul.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zeratul.service.UserService;

public class CheckUserNameServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3753018085076478595L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		UserService service=new UserService();
		boolean isExist=service.checkName(username);
			
		String json = "{\"isExist\":"+isExist+"}";
		response.getWriter().write(json);
			
	
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}