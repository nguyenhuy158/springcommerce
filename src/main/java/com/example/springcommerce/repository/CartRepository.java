package com.example.springcommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.springcommerce.model.Cart;

@Component
public interface CartRepository extends JpaRepository<Cart, Long> {

}
