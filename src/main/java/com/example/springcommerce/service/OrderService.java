package com.example.springcommerce.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springcommerce.dto.OrderDto;
import com.example.springcommerce.model.Order;
import com.example.springcommerce.model.OrderDetail;
import com.example.springcommerce.product.Product;
import com.example.springcommerce.repository.OrderDetailRepository;
import com.example.springcommerce.repository.OrderRepository;

@Service
public class OrderService {
	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderDetailRepository orderDetailRepository;

	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;

	public Order save(Order order) {
		return orderRepository.save(order);
	}

	public List<OrderDto> getOrders() {
		List<OrderDto> dtos = new ArrayList<>();
		String username = userDetailsServiceImpl.getCurrentUserId();

		List<Order> orderForCurrentUser = orderRepository.findByFullName(username);

		orderForCurrentUser
				.stream()
				.map(
						order -> {
							Long orderId = order.getId();
							Date orderDate = order.getOrderDate();
							List<OrderDetail> detailForCurrentOrder = orderDetailRepository
									.findByOrder(order);

							List<Product> products = detailForCurrentOrder
									.stream()
									.map(detail -> detail.getProduct())
									.toList();

							Double total = detailForCurrentOrder
									.stream().map(
											arg0 -> arg0.getQuanity() * arg0
													.getPrice())
									.reduce((double) 0,
											(arg0, arg1) -> arg0 + arg1);

							return new OrderDto(
									orderId,
									orderDate,
									detailForCurrentOrder,
									products,
									total);
						})
				.forEach(arg0 -> dtos.add(arg0));

		return dtos;
	}

	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	public void delete(Long id) throws Exception {
		Long count = orderRepository.countById(id);
		if (count == null || count == 0) {
			throw new Exception("Could not find any order with ID " + id);
		}

		System.out.println("-------------------------");
		System.out.println(orderRepository.findById(id).get());
		orderDetailRepository
				.findByOrder(orderRepository.findById(id).get()).forEach(System.out::println);

		orderDetailRepository
				.findByOrder(orderRepository.findById(id).get())
				.stream()
				.forEach(arg0 -> orderDetailRepository.delete(arg0));
		orderRepository.deleteById(id);
	}

}
