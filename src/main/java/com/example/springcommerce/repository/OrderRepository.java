package com.example.springcommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.springcommerce.model.Order;

@Component
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByFullName(String fullName);
}
