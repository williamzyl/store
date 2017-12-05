package com.zeratul.service;

import java.sql.SQLException;
import java.util.List;

import com.zeratul.bean.Product;
import com.zeratul.dao.ProductDao;
import com.zeratul.vo.Page;

public class ProductService {

	public Page getProducts (int currentpage,int currentCount) throws SQLException {
		ProductDao dao=new ProductDao();
		
		// 封装 分页对象
		Page page=new Page();
		
		page.setPage(currentpage);
		page.setCurrentCount(currentCount);
		
		int totalCount=dao.getProductTotal();
		page.setTotal(totalCount);
		
		 int totalPage = (int) Math.ceil(1.0*totalCount/currentCount);
		page.setTotalPage(totalPage);
		
		List<Product> products=dao.getProducts((currentpage-1)*currentCount,currentCount);
		
		page.setProducts(products);
		
		return page;
		
	}

	// 查询热门商品
	public List<Product> findHotProduct()  {
		ProductDao dao=new ProductDao();
		try {
			return dao.findHotProduct();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	// 	查询最新商品
	public List<Product> findNewProduct() {
		ProductDao dao=new ProductDao();
		try {
			return dao.findNewProduct();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Page getProductsByCategory(int currentpage,int currentCount,String cid) throws SQLException {
		
		ProductDao dao=new ProductDao();
		
		Page page=new Page();
		
		page.setCurrentCount(currentCount);
		page.setPage(currentpage);
		
		int total=dao.getProductTotalByCat(cid);
		page.setTotal(total);
		
		int totalPage = (int) Math.ceil(1.0*total/currentCount);
		page.setTotalPage(totalPage);
		
		List<Product> products = dao.getProductsByCat((currentpage-1)*currentCount, currentCount,cid);
		page.setProducts(products);
		
		return page;
		
	}

	public Product getProduct(String pid) throws SQLException {
		ProductDao dao=new ProductDao();
		return dao.getProduct(pid);
	}

	
	
}
