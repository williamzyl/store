package com.zeratul.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zeratul.bean.Category;
import com.zeratul.bean.Product;
import com.zeratul.dao.AdminCategoryDao;
import com.zeratul.service.AdminProductService;

public class AdminUpdateProductUI extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5489103211204207147L;

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String pid = request.getParameter("pid");
		
		AdminProductService service=new AdminProductService();
		
		try {
			Product product = service.getProductById(pid);
			request.setAttribute("product",product);
			
			List<Category> categories = service.getAllCategory();
			request.setAttribute("categories",categories);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		request.getRequestDispatcher("/admin/product/edit.jsp").forward(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}