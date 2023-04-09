package com.example.springcommerce.controller;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

  @RequestMapping(value = { "" }, method = RequestMethod.GET)
  public String viewCart(Model model) {
    model.addAttribute("isCart", true);
    model.addAttribute("isLogin", userDetailsServiceImpl.isLogin());
    model.addAttribute("pageTitle", "Shopping Cart");

    List<CartItem> carts = cartItemServiceImpl.getCurrentCartsByCurrentUser();
    Double totalPrice = cartItemServiceImpl.getTotalPrice();

    model.addAttribute("currentUser", userDetailsServiceImpl.getCurrentUser());
    model.addAttribute("cartId", ThreadLocalRandom.current().nextInt(1000, 10000));
    model.addAttribute("cartDate", DateTimeFormatter.ofPattern("dd-MM-yyyy").format(LocalDate.now()));
    model.addAttribute("carts", carts);
    model.addAttribute("subTotalPrice", new DecimalFormat("###,###").format(totalPrice));
    model.addAttribute("totalPrice", new DecimalFormat("###,###").format(totalPrice + 20000));

    return "cart";
  }

  @RequestMapping(value = { "/add" }, method = RequestMethod.POST)
  public String addToCart(@ModelAttribute("cartItem") CartItem cartItem) {
    cartItemServiceImpl.addCartItem(cartItem);
    return "redirect:/cart";
  }
}
