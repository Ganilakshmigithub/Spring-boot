package com.example.employeeapp.service;

import com.example.employeeapp.dto.UserDTO;
import com.example.employeeapp.entity.User;

public interface UserService {
    User registerUser (UserDTO userDTO);
    String login(UserDTO userDTO);
}