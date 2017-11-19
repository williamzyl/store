package com.zeratul.service;

import java.sql.SQLException;
import java.util.List;

import com.zeratul.bean.Category;
import com.zeratul.bean.Product;
import com.zeratul.dao.AdminCategoryDao;
import com.zeratul.dao.AdminProductDao;
import com.zeratul.vo.Condition;

public class AdminProductService {

	// 查询所有商品
	public List<Product> getProducts() throws SQLException {
		AdminProductDao dao=new AdminProductDao();
		return dao.queryProducts();
	}

	public List<Category> getAllCategory() throws SQLException {
		
		AdminCategoryDao dao=new AdminCategoryDao();
		
		return dao.queryCategorys();

	}

	public void addProduct(Product product) throws SQLException {
		AdminProductDao dao=new AdminProductDao();
		dao.addProduct(product);
	}

	public void delProduct(String pid) throws SQLException {
		AdminProductDao dao=new AdminProductDao();
		dao.delProduct(pid);
	}

	public Product getProductById(String pid) throws SQLException {
		AdminProductDao dao=new AdminProductDao();
		return dao.getProductById(pid);
	}

	public void updateProduct(Product product) throws SQLException {
		AdminProductDao dao=new AdminProductDao();
		 dao.UpdateProduct(product);
	}

	public List<Product> getPorductsByCondition(Condition condition) throws SQLException{
		AdminProductDao dao=new AdminProductDao();
		return dao.getPorductsByCondition(condition);
	}


}