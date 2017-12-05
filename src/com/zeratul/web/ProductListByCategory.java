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
public class ProductListByCategory extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 646448019379544153L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String cid = request.getParameter("cid");
		
		request.setAttribute("cid", cid);
		ProductService service=new ProductService();
		
		String currentPageStr = request.getParameter("currentPage");
		int currentPage=1;
		if(currentPageStr!=null){
			currentPage=Integer.valueOf(currentPageStr);
		}
		if(currentPage<=0){
			currentPage=1;
		}
		
		try {
			Page page =service.getProductsByCategory(currentPage,12,cid);
			request.setAttribute("page", page);
			
			Cookie[] cookies = request.getCookies();

			List<Product> historyProducts = new ArrayList<Product>();

			for (Cookie cookie : cookies) {
				if ("historyPid".equals(cookie.getName())) {
					String pids = cookie.getValue();
					System.out.println(pids);
					String[] pidList = pids.split(",");
					for (int i = 0; i < pidList.length; i++) {
						Product product = service.getProduct(pidList[i]);
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
		
		
	}
}