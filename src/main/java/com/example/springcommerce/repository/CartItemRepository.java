package com.example.springcommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import com.example.springcommerce.model.CartItem;

@Component
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    public CartItem findByProductId(Long productId);
}
