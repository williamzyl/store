package com.zeratul.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.zeratul.bean.Category;
import com.zeratul.bean.Product;
import com.zeratul.service.AdminProductService;
import com.zeratul.vo.Condition;

public class AdminGetProductsByCondition extends HttpServlet {

	/**
	 * 
	 */
	
	
	private static final long serialVersionUID = -2388308689003729550L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		
		Map<String, String[]> properties = request.getParameterMap();
		
		Condition condition=new Condition();
		
		try {
			BeanUtils.populate(condition, properties);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		
		AdminProductService service=new AdminProductService();
		try {
			List<Product> products = service.getPorductsByCondition(condition);
			System.out.println(products);
			request.setAttribute("products", products);
			
			List<Category> categorise = service.getAllCategory();
			
			request.setAttribute("categorise", categorise);
			
			request.setAttribute("condition", condition);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/admin/product/list.jsp").forward(request, response);
	
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}