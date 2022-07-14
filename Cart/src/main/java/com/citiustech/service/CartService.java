package com.citiustech.service;

import org.hibernate.procedure.ProcedureCall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.citiustech.model.Cart;
import com.citiustech.model.Product;
import com.citiustech.repository.CartRepository;


@Service
public class CartService {

	@Autowired
	CartRepository cartRepository;

	
	  @Autowired
	  
	  private RestTemplate restTemplate;
	  @Value("${microservice.search-service.endpoints.endpoint.uri}")
		private String ENDPOINT_URL;

	public Response addCart(Request request) {

		String response = null;
		Cart cart = request.getCart();
		Product product = request.getProduct();
		product.setCartId(cart.getCartId());
		product.setProductPrice(cart.getPrice());
		
		
		Product productResponse = restTemplate.postForObject(ENDPOINT_URL, product, Product.class);

				
			 cartRepository.save(cart);
			 return new Response(cart,productResponse.getProductId(),productResponse.getProductName(),productResponse.getProductPrice(),response);
	}
}
