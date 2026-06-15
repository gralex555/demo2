package com.alexey.order.controller;

import com.alexey.order.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/createUser")
    public void create(@RequestParam String username) {
        userService.createUser(username);
    }

    @DeleteMapping("/deleteUser/{id}")
    public void remove(@PathVariable int id) {
        userService.removeUser(id);
    }

    @GetMapping("/getUser/{id}")
    public String getUser(@PathVariable int id) {
       return userService.getUser(id);
    }
}
