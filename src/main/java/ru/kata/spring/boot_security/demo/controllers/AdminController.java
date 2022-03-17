package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping
public class AdminController {

    private final UserService userService;


    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String userList(Model model, Principal principal) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("allUsers", userService.findAll());
        model.addAttribute("adman", user);
        model.addAttribute("userThis", userService.loadUserByUsername(principal.getName()));
        return "adminPage";
    }

    @GetMapping(value = "/admin/add")
    public String newUser(Model model) {
        User user2 = new User();
        model.addAttribute("userNew", user2);
        return "adminPage";
    }

    @PostMapping(value = "/admin/add")
    public String createUser(@ModelAttribute("userNew") User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/edit/{id}")
    public String editPerson(@PathVariable("id") long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("userEdit", user);
        return "adminPage";
    }

    @PatchMapping("/update/{id}")
    public String update(@ModelAttribute("user") User user){
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }
}
