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

@Controller
@RequestMapping("/cart")
public class CartController {

  @Autowired
  CartItemServiceImpl cartItemServiceImpl;

  @GetMapping("")
  public String viewCart(Model model) {
    List<CartItem> carts = cartItemServiceImpl.getCurrentCartsByCurrentUser();
    Double totalPrice = cartItemServiceImpl.getTotalPrice();
    model.addAttribute("carts", carts);
    model.addAttribute("totalPrice", totalPrice);

    return "cart";
  }

  @PostMapping("/add")
  public String addToCart(@ModelAttribute("cartItem") CartItem cartItem) {
    System.out.println("cart posst add");

    System.out.println("cart item " + cartItem.toString());
    cartItemServiceImpl.addCartItem(cartItem);
    System.out.println("redirect:/products/" + cartItem.getProductId());
    return "redirect:/cart";
  }
}
