package com.example.springcommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springcommerce.model.OrderDetail;
import com.example.springcommerce.repository.OrderDetailRepository;

@Service
public class OrderDetailService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    public Object save(OrderDetail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }

}
