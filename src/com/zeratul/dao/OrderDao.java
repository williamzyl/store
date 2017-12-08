package com.zeratul.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.sql.Connection;
import com.zeratul.bean.Order;
import com.zeratul.bean.OrderItem;
import com.zeratul.utils.DataSourceUtils;

public class OrderDao {




	public void saveOrders(Order order) throws SQLException {
		QueryRunner runner=new QueryRunner();
		Connection conn=DataSourceUtils.getConnection();
		String sql="insert into orders value ( ?,?,?,?,?,?,?,? )";	
		runner.update(conn,sql,order.getOid(),order.getOrdertime(),order.getTotal(),order.getState()
				,order.getAddress(),order.getName(),order.getTelephone(),order.getUser().getUid());
	}

	public void saveOrderItem(List<OrderItem> items) throws SQLException {
		
		QueryRunner runner=new QueryRunner();
		Connection conn=DataSourceUtils.getConnection();
		String sql="insert into orderitem value (?,?,?,?,?) ";
		System.out.println(items);
		for (OrderItem orderItem : items) {
			runner.update(conn,sql,orderItem.getItemid(),orderItem.getCount(),orderItem.getSubTotal(),orderItem.getProduct().getPid()
					,orderItem.getOrder().getOid());
		}
		
	}

	public void confirmOrder(Order order) throws SQLException {
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		
		String sql="update orders set state=?,address=?,name=?,telephone=? where oid=?";
		
		runner.update(sql,order.getState(),order.getAddress(),order.getTelephone(),order.getOid());
		
	}

	public List<Order> getAllOrderByUser(String uid) throws SQLException {
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		
		String sql="select * from orders where uid= ?";
		List<Order> orderList = runner.query(sql, new BeanListHandler<Order>(Order.class),uid);
		
		return orderList;
		
	}

	public List<Map<String, Object>> getOrderItemByOrder(Order order) throws SQLException {
		
        QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		
		String sql="select o.count,o.subtotal,p.pimage,p.pname,p.shopPrice from orderitem o,product p where o.pid=p.pid and o.oid=?";
		
		List<Map<String, Object>> query = runner.query(sql, new MapListHandler(),order.getOid());
	
		return query;
		
	}

}
