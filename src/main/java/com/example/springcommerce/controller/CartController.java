package com.example.springcommerce.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.springcommerce.model.CartItem;
import com.example.springcommerce.repository.CartItemRepository;
import com.example.springcommerce.service.CartItemServiceImpl;

@Controller
@RequestMapping("/cart")
public class CartController {

  @Autowired
  CartItemRepository cartItemRepository;

  @Autowired
  CartItemServiceImpl cartItemServiceImpl;

  @PostMapping("/add")
  public String addToCart(@ModelAttribute("cartItem") CartItem cartItem) {
    System.out.println("cart posst add");

    System.out.println("cart item " + cartItem.toString());
    List<CartItem> cartItems = cartItemRepository.findAll();

    Optional<CartItem> itemOptional = cartItems.stream()
        .filter(item -> item.getProductId().equals(cartItem.getProductId()))
        .findFirst();

    if (itemOptional.isPresent()) {
      cartItemServiceImpl.updateQuantity(itemOptional.get().getId(), cartItem.getQuantity());
      // CartItem item = itemOptional.get();
      // item.setQuantity(item.getQuantity() + cartItem.getQuantity());

    } else {
      cartItemServiceImpl.save(cartItem);
      // cartItems.add(cartItem);
    }
    System.out.println("redirect:/products/" + cartItem.getProductId());
    return "redirect:/products/" + cartItem.getProductId();
  }
}
