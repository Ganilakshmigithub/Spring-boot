package com.example.employeeapp.entity;

import jakarta.persistence.*;
import lombok.Data;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Set<PhoneNumber> phoneNumbers = new HashSet<>();

    private String designation;
    private Double salary;
}