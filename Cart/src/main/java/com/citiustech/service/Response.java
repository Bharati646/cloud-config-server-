package com.citiustech.service;

import com.citiustech.model.Cart;
import com.citiustech.model.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class Response {
	
	
	public Response() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Response(Cart cart, Long id, String name, double price, String response) {
		super();
		this.cart = cart;
		
		this.id = id;
		this.name = name;
		this.price = price;
		this.response = response;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	

	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}

	/*
	 * public Product getProduct() { return product; } public void
	 * setProduct(Product product) { this.product = product; }
	 */
	private Cart cart;

	private Long id;
	private String name;
	private double price;
	String response;
}
