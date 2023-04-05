package com.example.springcommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springcommerce.model.Cart;
import com.example.springcommerce.model.CartItem;
import com.example.springcommerce.repository.CartItemRepository;
import com.example.springcommerce.repository.CartRepository;

@Service
public class CartItemServiceImpl {
    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    CartRepository cartRepository;

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

    public List<CartItem> getCurrentCarts() {
        return cartItemRepository.findAll();
    }

    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;

    public List<CartItem> getCurrentCartsByCurrentUser() {

        List<Cart> findAll = cartRepository.findAll();

        String currentUserId = userDetailsServiceImpl.getCurrentUserId();
        List<CartItem> list = findAll.stream().filter(arg0 -> arg0.getUserId().getUsername().equals(currentUserId))
                .map(arg0 -> cartItemRepository.findById(arg0.getCartItemId().getId()).get()).toList();

        return list;
    }
}
