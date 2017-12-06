package com.zeratul.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zeratul.bean.Category;
import com.zeratul.bean.Product;
import com.zeratul.service.CategoryService;
import com.zeratul.service.ProductService;


@Deprecated
public class ProductInfoServlet extends HttpServlet {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3674131375272235531L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		String pid = request.getParameter("pid");
		
		ProductService productService=new ProductService();	
		CategoryService categoryService=new CategoryService();
		
		
		
		try {
			Product product = productService.getProduct(pid);
			
			Category category = categoryService.getCategory(product.getCid());
			
			request.setAttribute("product", product);
			request.setAttribute("category", category);
			
			
			Cookie[] cookies = request.getCookies();
			String pids=null;
			if(cookies!=null){
			for (Cookie cookie : cookies) {
				if("historyPid".equals(cookie.getName())){
					pids = cookie.getValue();
					String[] productIds = pids.split(",");
					List<String> productList=Arrays.asList(productIds);
					LinkedList<String>  productLink=new LinkedList<>(productList);
					if(productLink.contains(pid)){
						productLink.remove(pid);
					}
					productLink.add(pid);
					
					StringBuffer sb=new StringBuffer();
					
					for (String string : productLink) {	
						sb.append(string);
						sb.append(',');
					}
					pids=sb.substring(0, sb.length()-1);
				}
			 }
			}else{
				pids=pid;
			}
			Cookie cookie=new Cookie("historyPid", pids);
			response.addCookie(cookie);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	
		
		
		
		request.getRequestDispatcher("/product_info.jsp").forward(request, response);
		
		
		
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}
}