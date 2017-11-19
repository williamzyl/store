package com.zeratul.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zeratul.bean.Product;
import com.zeratul.service.AdminProductService;

import org.apache.commons.beanutils.BeanUtils;

public class AdminAddProductServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6748321993981464897L;

	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// 设置字符为 UTF-8
		request.setCharacterEncoding("UTF-8");
		//  获取参数
	    Map<String, String[]> parameterMap = request.getParameterMap();
	    Product product=new Product();
	    
	    try {
	    	 // 封装对象
			BeanUtils.populate(product, parameterMap);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	    
	    
	    // 设置id， 图片。
	    product.setPid(UUID.randomUUID().toString());
	    product.setPimage("products/1/c_0033.jpg");
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String pdate = format.format(new Date());
		product.setPdate(pdate);
		product.setPflag(0);
		
	    AdminProductService service= new  AdminProductService();
	    
	    try {
			service.addProduct(product);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		request.getRequestDispatcher("/adminProductsServlet").forward(request, response);
	
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}