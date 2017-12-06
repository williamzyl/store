package com.zeratul.bean;

public class CartItem {
	
	private Product product;
	private int num;
	private double subTotal;
	
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public double getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}
	
	
	@Override
	public String toString() {
		return "CarItem [product=" + product + ", num=" + num + ", subTotal=" + subTotal + "]";
	}

	
	
	
	
	
	
	
}
