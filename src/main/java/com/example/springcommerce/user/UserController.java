package com.example.springcommerce.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.springcommerce.model.UserDetailsImp;
import com.example.springcommerce.service.UserDetailsServiceImpl;

@Controller
public class UserController {
    @Autowired
    private UserService service;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @GetMapping("/users")
    public String showUserList(Model model) {
        model.asMap().clear();
        model.addAttribute("isLogin", userDetailsServiceImpl.isLogin());
        model.addAttribute("pageTitle", "User List");
        model.addAttribute("isAdmin", userDetailsServiceImpl.isAdmin());
        model.addAttribute("isUser", true);

        List<UserDetailsImp> listUsers = userDetailsServiceImpl.getAllUses();
        model.addAttribute("listUsers", listUsers);

        return "users";
    }

    @GetMapping("/users/new")
    public String showNewForm(Model model) {
        model.addAttribute("isLogin", userDetailsServiceImpl.isLogin());
        model.addAttribute("pageTitle", "Add New User");
        model.addAttribute("isAdmin", userDetailsServiceImpl.isAdmin());

        model.addAttribute("user", new UserDetailsImp());
        return "user_form";
    }

    @PostMapping("/users/save")
    public String saveUser(UserDetailsImp user, RedirectAttributes ra) {

        System.out.println("before save" + user.toString());
        userDetailsServiceImpl.save(user);

        ra.addFlashAttribute("message", "The user has been saved successfully.");
        return "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    public String showEditForm(@PathVariable("id") String usename, Model model, RedirectAttributes ra) {
        model.addAttribute("isLogin", userDetailsServiceImpl.isLogin());
        try {
            UserDetailsImp user = userDetailsServiceImpl.get(usename);

            model.addAttribute("user", user);
            model.addAttribute("pageTitle", "Edit User (ID: " + usename + ")");

            return "user_form";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/users";
        }
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") String id, RedirectAttributes ra) {
        try {
            userDetailsServiceImpl.delete(id);
            ra.addFlashAttribute("message", "The user ID " + id + " has been deleted.");
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/users";
    }
}
