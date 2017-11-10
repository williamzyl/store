package com.zeratul.service;

import java.sql.SQLException;
import java.util.List;

import com.zeratul.bean.Product;
import com.zeratul.dao.AdminProductDao;

public class AdminProductService {

	// 查询所有商品
	public List<Product> getProducts() throws SQLException {
		AdminProductDao dao=new AdminProductDao();
		return dao.queryProducts();
	}



}