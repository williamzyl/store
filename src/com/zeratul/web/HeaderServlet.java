package com.zeratul.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.zeratul.bean.Category;
import com.zeratul.service.CategoryService;


public class HeaderServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4953137145934766567L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CategoryService service=new CategoryService();
		try {
			List<Category> categories = service.getAllCategory();
			Gson gson = new Gson();
			String categoriesJson = gson.toJson(categories);
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write(categoriesJson);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	
	
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}