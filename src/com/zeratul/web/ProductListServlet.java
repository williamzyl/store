package com.zeratul.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zeratul.bean.Product;
import com.zeratul.service.ProducServie;
import com.zeratul.vo.Page;

public class ProductListServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5671663480878072561L;

	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		
		ProducServie servie=new ProducServie();
		int currentPage=Integer.valueOf(request.getParameter("currentPage"));
		try {
			Page page = servie.getProducts(currentPage,12);
			System.out.println(page);
			request.setAttribute("page", page);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/product_list.jsp").forward(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}