package com.zeratul.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zeratul.service.AdminProductService;

public class AdminDelProductservlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1096529797102323323L;

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String pid = request.getParameter("pid");
		
		AdminProductService service=new AdminProductService();
		
		try {
			service.delProduct(pid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/adminProductsServlet").forward(request, response);
		
		
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}