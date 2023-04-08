package com.example.springcommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.springcommerce.model.Role;
import com.example.springcommerce.model.UserDetailsImp;
import com.example.springcommerce.service.UserDetailsServiceImpl;

@Controller
public class HomeController {

  @Autowired
  private UserDetailsServiceImpl userDetailsServiceImpl;

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
  public String index(Model model) {
    model.asMap().clear();
    model.addAttribute("isLogin", userDetailsServiceImpl.isLogin());
    model.addAttribute("pageTitle", "Spring Commerce");
    model.addAttribute("isHome", true);
    model.addAttribute("isAdmin", userDetailsServiceImpl.isAdmin());

    return "index";
  }

  @GetMapping("/login")
  public String viewLoginPage(Model model) {
    model.asMap().clear();
    model.addAttribute("isSignIn", true);
    model.addAttribute("pageTitle", "Sign In");
    model.addAttribute("isLogin", userDetailsServiceImpl.isLogin());
    return "login";
  }

  @GetMapping("/register")
  public String showRegistrationForm(Model model) {
    model.asMap().clear();
    model.addAttribute("isSignUp", true);
    model.addAttribute("pageTitle", "Sign Up");
    model.addAttribute("user", new UserDetailsImp());
    model.addAttribute("isLogin", userDetailsServiceImpl.isLogin());

    return "register";
  }

  @PostMapping("/register")
  public String createUser(UserDetailsImp userDetailsImp) {
    userDetailsImp.setRole(Role.USER);
    userDetailsImp.setActive(true);
    userDetailsImp.setPassword(bCryptPasswordEncoder.encode(userDetailsImp.getPassword()));
    userDetailsServiceImpl.save(userDetailsImp);
    return "redirect:/login";
  }
}
