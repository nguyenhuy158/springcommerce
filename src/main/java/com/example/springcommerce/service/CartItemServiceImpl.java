package com.example.springcommerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springcommerce.model.CartItem;
import com.example.springcommerce.repository.CartItemRepository;

@Service
public class CartItemServiceImpl {
    @Autowired
    private CartItemRepository cartItemRepository;

    public void save(CartItem cartItem) {
        CartItem cartItem2 = new CartItem(cartItem.getProductId(), cartItem.getQuantity());
        System.out.println("cart 2: " + cartItem2);
        cartItemRepository.save(cartItem2);
    }

    public void updateQuantity(Long id, int quantity) {
        Optional<CartItem> findById = cartItemRepository.findById(id);
        if (findById.isPresent()) {
            findById.get().setQuantity(findById.get().getQuantity() + quantity);
            cartItemRepository.save(findById.get());
        }
    }
}
