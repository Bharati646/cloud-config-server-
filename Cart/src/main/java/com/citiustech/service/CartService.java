package com.citiustech.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.citiustech.model.Cart;
import com.citiustech.model.Product;
import com.citiustech.repository.CartRepository;

public class CartService {

	@Autowired
	CartRepository cartRepository;

	@Autowired

	private RestTemplate restTemplate;

	public Cart addCart(Request request) {

		String response = null;
		Cart cart = request.getCart();
		Product product = request.getProduct();
		product.setCartId(cart.getCartId());
		product.setProductPrice(cart.getPrice());
		
		//Added to the cart
		return cartRepository.save(cart);

	
	}

}
