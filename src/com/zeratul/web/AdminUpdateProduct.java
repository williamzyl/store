package com.zeratul.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.zeratul.bean.Product;
import com.zeratul.service.AdminProductService;

public class AdminUpdateProduct extends HttpServlet {

	/** 
	 * 
	 */
	private static final long serialVersionUID = 2092041148394353787L;
	
	

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		Map<String, String[]> properties = request.getParameterMap();
	
		Product product=new Product();
		
		try {
			BeanUtils.populate(product, properties);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		SimpleDateFormat  dateFormat=new SimpleDateFormat("yyyy-mm-dd");
		String pdate = dateFormat.format(new Date());
		product.setPdate(pdate);
		
		
		AdminProductService service=new AdminProductService();
		try {
			service.updateProduct(product);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/adminProductsServlet").forward(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}