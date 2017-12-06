package com.zeratul.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zeratul.bean.Cart;
import com.zeratul.bean.CartItem;
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
	
	/**
	 * 首页
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		ProductService productService=new ProductService();
		
		List<Product> hotProducts = productService.findHotProduct();
		request.setAttribute("hotProducts", hotProducts);
		
		List<Product> newProducts = productService.findNewProduct();
		request.setAttribute("newProducts", newProducts);
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}
	
	/**
	 * 商品详情
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
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
	
   /**
    * 商品分类列表
    * @param request
    * @param response
    * @throws ServletException
    * @throws IOException
    */
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
	
	
	/**
	 * 加入购物车  存入session
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException 
	 */
	public void addShopCar(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException{
		
		ProductService service=new ProductService();
		
		String pid = request.getParameter("pid");
		
		int productNum = Integer.parseInt(request.getParameter("num"));
		
		HttpSession session = request.getSession();
		
		Cart cart = (Cart) session.getAttribute("cart");
		
		if(cart==null){
			cart=new Cart();
		}
		
		HashMap<String, CartItem> carItems = cart.getCarItems();
	
		CartItem cartItem;
		
		Product product;
		double subTotal=0d;
		if(carItems.get(pid)==null){
			cartItem=new CartItem();
			product = service.getProduct(pid);
			cartItem.setProduct(product);
			cartItem.setNum(productNum);
			
			subTotal=product.getShopPrice()*productNum;
			cartItem.setSubTotal(subTotal);
			carItems.put(pid, cartItem);
		
		}else{
			
			cartItem=carItems.get(pid);
			product=cartItem.getProduct();
			
			subTotal=product.getShopPrice()*productNum;
			
			cartItem.setSubTotal(subTotal+cartItem.getSubTotal());
			cartItem.setNum(productNum+cartItem.getNum());
		}
		
		
		double newTotal=cart.getTotalPrice()+subTotal;
		
		cart.setTotalPrice(newTotal);
		session.setAttribute("cart", cart);
		
		response.sendRedirect(request.getContextPath()+"/cart.jsp");
		
	}
	
	
	/**
	 *  删除单个商品
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void delFromCart(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		
		String pid=request.getParameter("pid");
		
		HttpSession session = request.getSession();
		
		Cart cart=(Cart) session.getAttribute("cart");
		
		if(cart!=null){
			HashMap<String, CartItem> carItems = cart.getCarItems();
			
			double subTotal= carItems.get(pid).getSubTotal();
			carItems.remove(pid);
			cart.setTotalPrice(cart.getTotalPrice()-subTotal);
		}
		
		
		session.setAttribute("cart", cart);
		
		response.sendRedirect(request.getContextPath()+"/cart.jsp");
		
	}

	/**
	 * 清空购物车
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void clearCart(HttpServletRequest request, HttpServletResponse response) throws IOException{
		HttpSession session = request.getSession();
		
		session.removeAttribute("cart");
		response.sendRedirect(request.getContextPath()+"/cart.jsp");
	}


}