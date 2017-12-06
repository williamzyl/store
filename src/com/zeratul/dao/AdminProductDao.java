package com.zeratul.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.zeratul.bean.Product;
import com.zeratul.utils.DataSourceUtils;
import com.zeratul.vo.Condition;

public class AdminProductDao {

	public List<Product> queryProducts() throws SQLException {
		
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from product;";
		List<Product> products = runner.query(sql,new BeanListHandler<Product>(Product.class));
		return products;
		
	}

	public void addProduct(Product product) throws SQLException {
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		
		String sql="insert into product values(?,?,?,?,?,?,?,?,?,?);";
		
		
		runner.update(sql,product.getPid(),product.getPname(),product.getMarketPrice(),
				product.getShopPrice(),product.getPimage(),product.getPdate(),product.getIsHot(),
				product.getPdesc(),product.getPflag(),product.getCid());
		
	}

	public void delProduct(String pid) throws SQLException {
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		
		String sql="delete from product where pid=?";
		
		runner.update(sql,pid);
		
	}

	public Product getProductById(String pid) throws SQLException {
        QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		
		String sql="select * from product where pid= ?";
		
		Product product=runner.query(sql, new BeanHandler<Product>(Product.class),pid);
		return product;
	}

	public void UpdateProduct(Product product) throws SQLException {
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		
		String sql="update product set pname =? ,marketPrice=?,shopPrice=?,pdate=?,isHot=?,pdesc=?,pflag=?,cid=? where pid=?";
		
		runner.update(sql, product.getPname(),product.getMarketPrice(),
				product.getShopPrice(),product.getPdate(),product.getIsHot(),
				product.getPdesc(),product.getPflag(),product.getCid(),product.getPid());
		
	}

	public List<Product> getPorductsByCondition(Condition condition) throws SQLException {
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		
		String sql="select * from product where 1=1";
		
		List<String> parms=new ArrayList<String>();
		if(condition.getPname()!=null && !"".equals(condition.getPname().trim())){
			sql+=" and pname like ? ";
			parms.add("%"+condition.getPname().trim()+"%");
		}
		
		if(condition.getCid()!=null && !"".equals(condition.getCid().trim())){
			sql+=" and cid= ? ";
			parms.add(condition.getCid());
		}
		
		if(condition.getIsHot()!=null && !"".equals(condition.getIsHot().trim())){
			sql+=" and isHot= ? ";
			parms.add(condition.getIsHot());
		}
		List<Product> products=runner.query(sql, new BeanListHandler<Product>(Product.class),parms.toArray());
		return products;
	}
	
	
	
}
