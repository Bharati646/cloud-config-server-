package com.citiustech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.citiustech.model.Cart;
import com.citiustech.model.Product;




@Repository
public interface CartRepository  extends JpaRepository<Cart, Integer> {

}