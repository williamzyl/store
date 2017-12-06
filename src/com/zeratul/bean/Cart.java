package com.zeratul.bean;

import java.util.HashMap;

public class Cart {

	private HashMap<String, CartItem> carItems=new HashMap<String,CartItem>();
	
	private double totalPrice ;

	
	
	public HashMap<String, CartItem> getCarItems() {
		return carItems;
	}

	public void setCarItems(HashMap<String, CartItem> carItems) {
		this.carItems = carItems;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	
	
	
	
}
