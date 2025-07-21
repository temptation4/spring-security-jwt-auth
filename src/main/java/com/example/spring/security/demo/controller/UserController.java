package com.example.spring.security.demo.controller;

import com.example.spring.security.demo.entity.User;
import com.example.spring.security.demo.service.MyUserDetails;
import com.example.spring.security.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 🔹 Create new user
    @PostMapping("/save")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody User user) {
        return userService.verify(user);
    }

    // 🔹 Get all users
    @GetMapping("get_all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Optional: Secure test endpoint
    @GetMapping("/admin")
    public String adminAccess() {
        return "Hello, ADMIN!";
    }

    @GetMapping("/user")
    public String userAccess() {
        return "Hello, USER!";
    }

    @GetMapping("/")
    public String home() {
        return "Welcome to the secured app!";
    }

}
