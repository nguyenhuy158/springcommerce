package com.example.springcommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.springcommerce.model.CartItem;
import com.example.springcommerce.service.CartItemServiceImpl;
import com.example.springcommerce.service.UserDetailsServiceImpl;

@Controller
@RequestMapping("/cart")
public class CartController {

  @Autowired
  private UserDetailsServiceImpl userDetailsServiceImpl;

  @Autowired
  CartItemServiceImpl cartItemServiceImpl;

  @GetMapping("")
  public String viewCart(Model model) {
    model.addAttribute("isLogin", userDetailsServiceImpl.isLogin());
    model.addAttribute("pageTitle", "Shopping Cart");

    List<CartItem> carts = cartItemServiceImpl.getCurrentCartsByCurrentUser();
    Double totalPrice = cartItemServiceImpl.getTotalPrice();
    model.addAttribute("carts", carts);
    model.addAttribute("totalPrice", totalPrice);

    return "cart";
  }

  @PostMapping("/add")
  public String addToCart(@ModelAttribute("cartItem") CartItem cartItem) {
    cartItemServiceImpl.addCartItem(cartItem);
    return "redirect:/cart";
  }
}
