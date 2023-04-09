package com.example.springcommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.springcommerce.service.UserDetailsServiceImpl;

@Controller
public class HomeController {

  @Autowired
  private UserDetailsServiceImpl userDetailsServiceImpl;

  @RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
  public String index(Model model) {
    model.asMap().clear();
    model.addAttribute("isLogin", userDetailsServiceImpl.isLogin());
    model.addAttribute("pageTitle", "Spring Commerce");
    model.addAttribute("isHome", true);
    model.addAttribute("isAdmin", userDetailsServiceImpl.isAdmin());

    return "index";
  }
}
