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

	public Product save(Product product) {
		return searchRepository.save(product);
	}

	public Product getById(Long id) {
		Product product = searchRepository.findById(id).get();

		return product;
	}

	public Product getByName(String name) {
		Product product = searchRepository.findByName(name);

		return product;
	}

}
