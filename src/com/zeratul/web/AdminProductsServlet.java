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
import com.zeratul.service.AdminProductService;


public class AdminProductsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	
	
	
	
    public AdminProductsServlet() {
    	
    }

    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		AdminProductService service=new AdminProductService();
		
		List<Product> products;
		
		try {
			products = service.getProducts();
			request.setAttribute("products", products);
			
			List<Category> categorise = service.getAllCategory();
			
			request.setAttribute("categorise", categorise);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/admin/product/list.jsp").forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
