package com.zeratul.service;

import java.sql.SQLException;
import java.util.List;

import com.zeratul.bean.Product;
import com.zeratul.dao.ProductDao;
import com.zeratul.vo.Page;

public class ProducServie {

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

	
	
}
