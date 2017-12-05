package com.zeratul.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zeratul.bean.Product;
import com.zeratul.service.ProductService;
import com.zeratul.vo.Page;
@Deprecated
public class ProductListServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5671663480878072561L;

	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		
		ProductService servie=new ProductService();
		String currentPageStr = request.getParameter("currentPage");
		int currentPage=1;
		if(currentPageStr!=null){
			currentPage=Integer.valueOf(currentPageStr);
		}
		if(currentPage<=0){
			currentPage=1;
		}
		
		try {
			Page page = servie.getProducts(currentPage,12);
			request.setAttribute("page", page);
			
			Cookie[] cookies = request.getCookies();
			
			List<Product> historyProducts=new ArrayList<Product>();
			
			for (Cookie cookie : cookies) {
				if("historyPid".equals(cookie.getName())){
					String pids=cookie.getValue();
					System.out.println(pids);
					String[] pidList = pids.split(",");
					for(int i=0;i<pidList.length;i++){
						Product product = servie.getProduct(pidList[i]);
						System.out.println(product);
						historyProducts.add(product);
					}
					request.setAttribute("historyProducts", historyProducts);
					break;
				}
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/product_list.jsp").forward(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}