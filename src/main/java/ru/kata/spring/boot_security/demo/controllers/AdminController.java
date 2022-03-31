package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.UserService;


@Controller
public class AdminController {

    @GetMapping("/")
    public String toLogin() {
        return "redirect:/login";
    }

    @GetMapping("/admin")
    public String showAllUsers(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("admen", user);
        return "admin";
    }

    @GetMapping("/user")
    public String showUserForUser(Model userModel) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userModel.addAttribute("man", user);
        return "user";
    }
}
