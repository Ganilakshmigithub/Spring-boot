package com.example.employeeapp.service;

import com.example.employeeapp.dto.EmployeeDTO;
import com.example.employeeapp.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee saveEmployee(EmployeeDTO employeeDTO);
    List<Employee> getAllEmployees();
}