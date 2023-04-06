package com.example.springcommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.springcommerce.model.Role;
import com.example.springcommerce.model.UserDetailsImp;
import com.example.springcommerce.service.UserDetailsServiceImpl;

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
  public String viewLoginPage(Model model) {
    return "login";
  }

  @GetMapping("/register")
  public String showRegistrationForm(Model model) {
    model.addAttribute("user", new UserDetailsImp());

    return "register";
  }

  @Autowired
  private UserDetailsServiceImpl userDetailsServiceImpl;

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @PostMapping("/register")
  public String createUser(UserDetailsImp userDetailsImp, RedirectAttributes ra) {

    userDetailsImp.setRole(Role.USER);
    userDetailsImp.setActive(true);
    userDetailsImp.setPassword(bCryptPasswordEncoder.encode(userDetailsImp.getPassword()));

    userDetailsServiceImpl.save(userDetailsImp);
    return "redirect:/login";
  }
}
