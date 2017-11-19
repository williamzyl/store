package com.zeratul.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.zeratul.bean.Product;
import com.zeratul.utils.DataSourceUtils;
import com.zeratul.vo.Page;

public class ProductDao {


	public List<Product> getProducts(int index, int pageCount) throws SQLException {
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		
		String sql="select * from product limit ?,?";
		System.out.println(index);
		System.out.println(pageCount);
		List<Product> porducts = runner.query(sql, new BeanListHandler<Product>(Product.class),index,pageCount);
		
		return porducts;
	}

	
	
	public int getProductTotal() throws SQLException{
		
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select count(*) from product";
		Long count = (Long) runner.query(sql, new ScalarHandler());
		return count.intValue();
	}
	
}
