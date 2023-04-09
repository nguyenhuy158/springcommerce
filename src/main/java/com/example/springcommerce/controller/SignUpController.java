package com.example.springcommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.springcommerce.emun.Role;
import com.example.springcommerce.model.UserDetailsImp;
import com.example.springcommerce.service.UserDetailsServiceImpl;

@Controller
public class SignUpController {

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping(value = { "/register" }, method = RequestMethod.GET)
    public String SignUp(Model model) {
        model.asMap().clear();
        model.addAttribute("isSignUp", true);
        model.addAttribute("pageTitle", "Sign Up");
        model.addAttribute("user", new UserDetailsImp());
        model.addAttribute("isLogin", userDetailsServiceImpl.isLogin());

        return "register";
    }

    @RequestMapping(value = { "/register" }, method = RequestMethod.POST)
    public String createUserDetail(UserDetailsImp userDetailsImp) {
        userDetailsImp.setRole(Role.USER);
        userDetailsImp.setActive(true);
        userDetailsImp.setPassword(bCryptPasswordEncoder.encode(userDetailsImp.getPassword()));
        userDetailsServiceImpl.save(userDetailsImp);
        return "redirect:/login";
    }
}
