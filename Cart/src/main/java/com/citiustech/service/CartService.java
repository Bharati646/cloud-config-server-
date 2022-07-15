package com.citiustech.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.citiustech.model.Cart;
import com.citiustech.model.Order;
import com.citiustech.model.Product;
import com.citiustech.repository.CartRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RefreshScope
@Service
public class CartService {

	@Autowired
	CartRepository cartRepository;

	@Autowired
	private RestTemplate restTemplate;

	Logger logger = LoggerFactory.getLogger(Cart.class);
	
	@Value("${microservice.search-service.endpoints.endpoint.uri}")
	private String ENDPOINT_URL;

	@Value("${microservice.order-service.endpoints.doPayment.uri}")
	private String orderEndpointUrl;

	public Product getById(int id) throws JsonProcessingException {
		logger.info("Cart Service Request: {}", new ObjectMapper().writeValueAsString(id));

		Product product = restTemplate.getForObject("ENDPOINT_URL" + "/" + id, Product.class);

		return product;
	}

	public Cart addCart(Cart cart) throws JsonProcessingException {
	
		logger.info("Cart Service Request: {}", new ObjectMapper().writeValueAsString(cart));


		Product product1 = getById(1);
		Product product2 = getById(2);
		Product product3 = getById(3);
		List<Product> p = Arrays.asList(product1, product2, product3);

		LongSummaryStatistics lss = p.stream().collect(Collectors.summarizingLong(prod -> prod.getProductPrice()));

		cart.setQty(p.size());

		cart.setPrice(lss.getSum());
		cart = cartRepository.save(cart);
		Order order = new Order();

		order.setAmount(cart.getPrice());
		order.setTotalQuantity(cart.getQty());
		order.setUserId(cart.getUserId());
		order.setCartId(cart.getCartId());
		Order orderResponse = restTemplate.postForObject(orderEndpointUrl, order, Order.class);
		//
		return cart;

	}

}