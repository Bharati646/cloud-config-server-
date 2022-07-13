package com.citiustech.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import com.citiustech.model.Product;
import com.citiustech.service.SearchService;


import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/product")
public class SearchController {
	
	@Autowired
	private SearchService searchService;

	@PostMapping("/saveProduct")
	public ResponseEntity<Product> save(@RequestBody Product product) {
		Product saveProduct = searchService.save(product);
		return new ResponseEntity<Product>(saveProduct, HttpStatus.CREATED);
	}
	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) {
		Product product = searchService.getById(id);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
	@GetMapping("/name")
	public ResponseEntity<Product> getProductByName(@RequestParam String name) {
		Product product = searchService.getByName(name);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
}
