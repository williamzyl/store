package com.zeratul.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zeratul.bean.Category;
import com.zeratul.bean.Product;
import com.zeratul.service.CategoryService;
import com.zeratul.service.ProductService;
import com.zeratul.vo.Page;

public class ProductServlet extends BaseServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3204168534858951099L;



//	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		
//		
//		
//		
//	}
//
//	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doGet(request, response);
//	}
//	
	
	
	public void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		ProductService productService=new ProductService();
		
		List<Product> hotProducts = productService.findHotProduct();
		request.setAttribute("hotProducts", hotProducts);
		
		List<Product> newProducts = productService.findNewProduct();
		request.setAttribute("newProducts", newProducts);
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}
	
	
   public void productInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
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
	
	public void productList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
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
					String[] pidList = pids.split(",");
					for (int i = 0; i < pidList.length; i++) {
						Product product = service.getProduct(pidList[i]);
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
	
	
	
	
}