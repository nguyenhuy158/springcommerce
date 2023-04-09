package com.example.springcommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.springcommerce.dto.OrderDto;
import com.example.springcommerce.service.OrderService;
import com.example.springcommerce.service.UserDetailsServiceImpl;

@Controller
public class HistoryController {

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    OrderService orderService;

    @RequestMapping(value = { "/history" }, method = RequestMethod.GET)
    public String history(Model model) {
        model.asMap().clear();
        model.addAttribute("isLogin", userDetailsServiceImpl.isLogin());
        model.addAttribute("pageTitle", "History");
        model.addAttribute("isHistory", true);
        model.addAttribute("isAdmin", userDetailsServiceImpl.isAdmin());

        List<OrderDto> orderDtos = orderService.getOrders();
        model.addAttribute("orders", orderDtos);
        return "history";
    }
}
