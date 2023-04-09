package com.example.springcommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.springcommerce.service.UserDetailsServiceImpl;

@Controller
public class AboutController {

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @RequestMapping(value = { "/about" }, method = RequestMethod.GET)
    public String about(Model model) {
        model.asMap().clear();
        model.addAttribute("isLogin", userDetailsServiceImpl.isLogin());
        model.addAttribute("pageTitle", "About");
        model.addAttribute("isAbout", true);
        model.addAttribute("isAdmin", userDetailsServiceImpl.isAdmin());

        return "about";
    }

}
