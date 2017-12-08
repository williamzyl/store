package com.zeratul.bean;

public class OrderItem {

	
//	`itemid` varchar(32) NOT NULL,
//	  `count` int(11) DEFAULT NULL,
//	  `subtotal` double DEFAULT NULL,
//	  `pid` varchar(32) DEFAULT NULL,
//	  `oid` varchar(32) DEFAULT NULL,
	
	private String itemid;
	private int count;
	private double subTotal;
	private Product product;
	private Order order;
	
	
	public String getItemid() {
		return itemid;
	}
	public void setItemid(String itemid) {
		this.itemid = itemid;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	public double getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	
	
	@Override
	public String toString() {
		return "OrderItem [itemid=" + itemid + ", count=" + count + ", subTotal=" + subTotal + ", product=" + product
				+ ", order=" + order + "]";
	}
	
	
	
}
