package com.example.employeeapp.entity;

import lombok.Data;

import jakarta.persistence.*;

@Entity
@Data
@Table(name="userandrole")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    private String role; // e.g., "ROLE_USER", "ROLE_ADMIN"
}