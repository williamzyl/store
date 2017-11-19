package com.zeratul.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.zeratul.bean.Category;
import com.zeratul.bean.Product;
import com.zeratul.utils.DataSourceUtils;

public class AdminCategoryDao {

	
	
	public List<Category> queryCategorys() throws SQLException {
		QueryRunner runner=new  QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from category";
		List<Category> categories=runner.query(sql, new BeanListHandler<Category>(Category.class));
		return categories;
	}

	

	
	
	
}
