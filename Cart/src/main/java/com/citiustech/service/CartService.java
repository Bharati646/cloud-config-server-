package com.citiustech.service;

import java.util.ArrayList;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.citiustech.model.Cart;
import com.citiustech.model.Product;
import com.citiustech.repository.CartRepository;

@RefreshScope
@Service
public class CartService {

	@Autowired
	CartRepository cartRepository;

	@Autowired

	private RestTemplate restTemplate;
	@Value("${microservice.search-service.endpoints.endpoint.uri}")
	private String ENDPOINT_URL;

	@Value("${microservice.order-service.endpoints.doPayment.uri}")
	private String orderEndpointUrl;

	Product product;

	public Product getById(int id) {

		product = restTemplate.getForObject("ENDPOINT_URL" + "/" + id, Product.class);

		return product;
	}

	public Response addCart(Cart cart) {
		String response = null;

		List<Product> p = new ArrayList<>();

		/*
		 * Product product1 = getById(1); Product product2 = getById(2); Product
		 * product3 = getById(3);
		 */
		p.add(new Product(product.getId(), product.getProductDesc(), product.getProductName(),
				product.getProductPrice(), product.getCartId()));

	

		product.setProductPrice((long) cart.getPrice());
		LongSummaryStatistics lss = p.stream().collect(Collectors.summarizingLong(prod -> prod.getProductPrice()));

		cart.setQty(cart.getQty());
		long price = lss.getSum();
		Product productResponse = restTemplate.postForObject(orderEndpointUrl, product, Product.class);

		cartRepository.save(cart);
		cart.setPrice(lss.getSum());

		return new Response(cart, productResponse.getId(), productResponse.getProductName(),
				productResponse.getProductPrice(), response);

	}

}