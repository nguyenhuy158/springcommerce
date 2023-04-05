package com.example.springcommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.springcommerce.model.OrderDetail;

@Component
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}
