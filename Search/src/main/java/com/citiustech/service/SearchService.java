package com.citiustech.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.citiustech.model.Product;
import com.citiustech.repository.SearchRepository;

@Service
public class SearchService {

	@Autowired
	private SearchRepository searchRepository;
	
	
	@Autowired
	
	private RestTemplate restTemplate;
	
	@Value("${microservice.seach-service.endpoints.endpoint.uri}")
	private String ENDPOINT_URL;
	
	public Product save(Product product) {
		return searchRepository.save(product);
	}
	
	public Product getById(Long id) {
		Product product = searchRepository.findById(id).get();
		if(product == null) {
			product = restTemplate.getForObject("ENDPOINT_URL"+"/"+id, Product.class);
			
		}
		return product;
	}
	public Product getByName(String name) {
		Product product = searchRepository.findByName(name);
		if(product == null) {
			product = restTemplate.getForObject("ENDPOINT_URL"+"/"+name, Product.class);
		}
		return product;
	}
	
}
