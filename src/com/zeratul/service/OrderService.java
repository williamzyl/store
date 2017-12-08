package com.zeratul.service;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.zeratul.bean.Order;
import com.zeratul.bean.OrderItem;
import com.zeratul.bean.Product;
import com.zeratul.bean.User;
import com.zeratul.dao.OrderDao;
import com.zeratul.utils.DataSourceUtils;

public class OrderService {

	public boolean submitOrder(Order order) {
		
		OrderDao dao=new OrderDao();
		
		try {
			DataSourceUtils.startTransaction();
			
			dao.saveOrders(order);
			
			dao.saveOrderItem(order.getItems());
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				System.out.println("callback");
				DataSourceUtils.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			try {System.out.println("commitAndRelease");
				DataSourceUtils.commitAndRelease();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return false;
		
	}

	public void confirmOrder(Order order) {
		OrderDao dao=new OrderDao();
		
		try {
			dao.confirmOrder(order);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public List<Order> getAllOrderByUser(User user) throws SQLException {
		
		OrderDao dao=new OrderDao();
		List<Order> orderList = dao.getAllOrderByUser(user.getUid());
		
		for (Order order : orderList) {
			List<Map<String, Object>> mapList = dao.getOrderItemByOrder(order);
			
			List<OrderItem> orderItems=new ArrayList<OrderItem>();
			for (Map<String, Object> map : mapList) {
				
				try {
					OrderItem item=new OrderItem();
					BeanUtils.populate(item, map);
					Product product=new Product();
					BeanUtils.populate(product, map);
					item.setProduct(product);
					orderItems.add(item);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
				
			}
			order.setItems(orderItems);
		}
		
		return orderList;
		
	}
	
	
}
