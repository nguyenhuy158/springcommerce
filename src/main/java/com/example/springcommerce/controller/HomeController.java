package com.example.springcommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

  @RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
  public String index(Model model) {
    System.out.println("log log");
    return "index";
  }

  @RequestMapping(value = { "/demo" }, method = RequestMethod.POST)
  public String demo(Model model) {
    return "demo";
  }

  @GetMapping("/login")
  public String viewLoginPage() {
    return "login";
  }
}
