package com.example.springcommerce.dto;

import java.util.Date;
import java.util.List;

import com.example.springcommerce.model.OrderDetail;
import com.example.springcommerce.product.Product;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderDto {

    private Long orderId;

    private Date orderDate;

    private List<OrderDetail> orderDetails;

    private List<Product> products;

    private Double total;
}
