package com.citiustech.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.citiustech.model.Product;
import com.citiustech.repository.SearchRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class SearchService {

	@Autowired
	private SearchRepository searchRepository;

	@Autowired
	private RestTemplate restTemplate;

	Logger logger = LoggerFactory.getLogger(Product.class);
	@Value("${microservice.product-catalog-service.endpoints.doPayment.uri}")
	private String productCatalogEndpointUrl;

	public Product getById(Long id) throws JsonProcessingException {
		logger.info("SEARCH_SERVICE Request: {}", new ObjectMapper().writeValueAsString(id));

		Product product = restTemplate.getForObject("productCatalogEndpointUrl" + "/" + id, Product.class);

		return product;
	}

	public Product getByName(String name) throws JsonProcessingException {
		logger.info("SEARCH_SERVICE Request: {}", new ObjectMapper().writeValueAsString(name));

		Product product = restTemplate.getForObject("productCatalogEndpointUrl" + "/" + name, Product.class);

		return product;
	}

}
