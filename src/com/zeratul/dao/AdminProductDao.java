package com.zeratul.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.zeratul.bean.Product;
import com.zeratul.utils.DataSourceUtils;

public class AdminProductDao {

	public List<Product> queryProducts() throws SQLException {
		
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from product;";
		List<Product> products = runner.query(sql,new BeanListHandler<Product>(Product.class));
		return products;
		
	}

	
	
	
}
