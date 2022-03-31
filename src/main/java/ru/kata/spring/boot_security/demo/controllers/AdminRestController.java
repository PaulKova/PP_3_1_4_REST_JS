package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.ExseptionHandling.NoUserByIdException;

import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/api")
public class AdminRestController {

    private final UserService userService;

    public AdminRestController(UserService userService) {
        this.userService = userService;
    }
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    @GetMapping("/admin")
    public List<User> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return users;
    }

    @GetMapping("/admin/{id}")
    public User getUserByUserId(@PathVariable Long id){
        User user = userService.getUser(id);
        if (user == null){
            throw new NoUserByIdException("Юзера с id = " + id + ", не сущетсвует");
        }
        return user;
    }

    @GetMapping("/user")
    public ResponseEntity<User> showUserForUser() {
        return ResponseEntity.ok((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()) ;
    }

    @GetMapping("/roles")
    public ResponseEntity<Set<Role>> getAllRoles() {
        return  ResponseEntity.ok(userService.getRoles());
    }

    @PostMapping("/admin/create")
    public void createUser(@ModelAttribute User user,Role role) {
        role.getRole();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userService.addUser(user);
    }

    @PatchMapping("/edit/{id}")
    public void updateUser(@ModelAttribute User user) {
        userService.updateUser(user);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUserById(@PathVariable Long id){
        User user = userService.getUser(id);
        if (user == null){
            throw  new NoUserByIdException("Юзера с id = " + id + ", не сущетсвует");
        }
        userService.deleteUser(id);
        return "Юзер с id = " + id + ", успешно удален";
    }

}

