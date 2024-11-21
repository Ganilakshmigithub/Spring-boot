package com.example.employeeapp.controller;

import com.example.employeeapp.dto.UserDTO;
import com.example.employeeapp.entity.User;
import com.example.employeeapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody UserDTO userDTO) {
        return userService.registerUser (userDTO);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserDTO userDTO) {
        return userService.login(userDTO);
    }
}