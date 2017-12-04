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
		List<Product> porducts = runner.query(sql, new BeanListHandler<Product>(Product.class),index,pageCount);
		
		return porducts;
	}

	public List<Product> getProductsByCat(int index, int pageCount,String cid) throws SQLException {
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		
		String sql="select * from product where cid=? limit ?,?";
		List<Product> porducts = runner.query(sql, new BeanListHandler<Product>(Product.class),cid,index,pageCount);
		
		return porducts;
	}
	
	
	public int getProductTotal() throws SQLException{
		
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select count(*) from product";
		Long count = (Long) runner.query(sql, new ScalarHandler());
		return count.intValue();
	}

	public int getProductTotalByCat(String cid) throws SQLException{
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select count(*) from product where cid=?";
		Long count = (Long) runner.query(sql, new ScalarHandler(),cid);
		return count.intValue();
	}

	public List<Product> findHotProduct() throws SQLException {
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from product where isHot=? limit ?,?";
		List<Product> products = runner.query(sql, new BeanListHandler<Product>(Product.class),1,0,9);
		return products;
	}



	public List<Product> findNewProduct() throws SQLException {
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from product order by pdate desc limit ?,?";
		List<Product> products = runner.query(sql, new BeanListHandler<Product>(Product.class),0,9);
		return products;
	}
	
}
