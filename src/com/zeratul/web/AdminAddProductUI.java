package com.zeratul.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zeratul.bean.Category;
import com.zeratul.service.AdminProductService;

public class AdminAddProductUI extends HttpServlet {

	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5994221117313758270L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		AdminProductService service=new AdminProductService();
		
		try {
			List<Category> categories = service.getAllCategory();
			request.setAttribute("categories", categories);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/admin/product/add.jsp").forward(request, response);
	
	
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}