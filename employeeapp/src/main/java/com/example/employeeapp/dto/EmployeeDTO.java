package com.example.employeeapp.dto;

import lombok.Data;

import java.util.Set;

@Data
public class EmployeeDTO {
    private Long id;
    private String name;
    private Set<String> phoneNumbers;
    private String designation;
    private Double salary;
}