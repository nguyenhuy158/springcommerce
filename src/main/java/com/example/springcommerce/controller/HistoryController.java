package com.example.springcommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.springcommerce.dto.OrderDto;
import com.example.springcommerce.model.Order;
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

        if (userDetailsServiceImpl.isAdmin()) {
            List<Order> orders = orderService.getAllOrders();
            model.addAttribute("allOrders", orders);
        } else {
            List<OrderDto> orderDtos = orderService.getOrders();
            model.addAttribute("orders", orderDtos);
        }
        return "history";
    }

    @GetMapping("/orders/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id, RedirectAttributes ra) {
        try {
            orderService.delete(id);
            ra.addFlashAttribute("message", "The Order ID " + id + " has been deleted.");
        } catch (Exception e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/history";
    }
}
