package com.example.employeeapp.dto;

import lombok.Data;

@Data
public class UserDTO {
    private String email;
    private String password;
    private String role;
}