package com.zeratul.vo;

import java.util.List;

import com.zeratul.bean.Product;

public class Page {
	
	private int page;
	private int currentCount;
	private int total;
	private int totalPage;
	
	
	private List<Product>  products;
	
	
	
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getCurrentCount() {
		return currentCount;
	}
	public void setCurrentCount(int currentCount) {
		this.currentCount = currentCount;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	@Override
	public String toString() {
		return "Page [page=" + page + ", currentCount=" + currentCount + ", total=" + total + ", totalPage=" + totalPage
				+ "]";
	}
	
	
}
