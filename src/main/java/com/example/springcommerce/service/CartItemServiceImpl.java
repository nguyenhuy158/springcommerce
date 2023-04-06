package com.example.springcommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        // System.out.println("cart 2: " + cartItem2);
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

    @Transactional
    public void clearCurrentCartByCurrentUser() {
        List<CartItem> currentCartsByCurrentUser = getCurrentCartsByCurrentUser();
        cartRepository.deleteByUserId(userDetailsServiceImpl.getCurrentUser());
        currentCartsByCurrentUser.stream().forEach(
                arg0 -> cartItemRepository.deleteById(arg0.getId()));

    }

    public Double getTotalPrice() {
        List<CartItem> currentCartsByCurrentUser = getCurrentCartsByCurrentUser();

        Double reduce = currentCartsByCurrentUser.stream().map(arg0 -> arg0.getProductPrice() * arg0.getQuantity())
                .reduce(0.0D,
                        (arg0, arg1) -> arg0 + arg1);
        return reduce + 0.0D;
    }

    public void addCartItem(CartItem cartItem) {
        List<CartItem> cartItems = getCurrentCartsByCurrentUser();

        Optional<CartItem> itemOptional = cartItems.stream()
                .filter(item -> item.getProductId().equals(cartItem.getProductId()))
                .findFirst();

        if (itemOptional.isPresent()) {
            updateQuantity(itemOptional.get().getId(), cartItem.getQuantity());
            // CartItem item = itemOptional.get();
            // item.setQuantity(item.getQuantity() + cartItem.getQuantity());
            cartItemRepository.save(itemOptional.get());
        } else {

            CartItem save = cartItemRepository.save(cartItem);
            // System.out.println("create new cart item: " + save);

            Cart cart = new Cart();
            cart.setCartItemId(save);
            cart.setUserId(userDetailsServiceImpl.getCurrentUser());

            // System.out.println("new cart: " + cart);
            cartRepository.save(cart);

            // save(cartItem);
            // cartItems.add(cartItem);
        }
    }

}
