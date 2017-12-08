package com.zeratul.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
	
//	`oid` varchar(32) NOT NULL,
//	  `ordertime` datetime DEFAULT NULL,
//	  `total` double DEFAULT NULL,
//	  `state` int(11) DEFAULT NULL,
//	  `address` varchar(30) DEFAULT NULL,
//	  `name` varchar(20) DEFAULT NULL,
//	  `telephone` varchar(20) DEFAULT NULL,
//	  `uid` varchar(32) DEFAULT NULL,
	
	
	private String oid;
	private Date ordertime;
	private double total;
	private int state;
	private String name;
	private String address;
	private String telephone;
	private User user;
	
	private List<OrderItem> items=new ArrayList<OrderItem>();
	
	
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	
	public Date getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<OrderItem> getItems() {
		return items;
	}
	public void setItems(List<OrderItem> items) {
		this.items = items;
	}
	
	@Override
	public String toString() {
		return "Order [oid=" + oid + ", ordertime=" + ordertime + ", total=" + total + ", state=" + state + ", name="
				+ name + ", address=" + address + ", telephone=" + telephone + ", user=" + user + "]";
	}

}
