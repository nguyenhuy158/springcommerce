package com.example.springcommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.springcommerce.model.Order;
import com.example.springcommerce.model.OrderDetail;

@Component
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

    List<OrderDetail> findByOrder(Order order);
}
