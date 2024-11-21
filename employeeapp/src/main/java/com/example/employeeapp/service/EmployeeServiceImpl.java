package com.example.employeeapp.service;
import com.example.employeeapp.dto.EmployeeDTO;
import com.example.employeeapp.entity.Employee;
import com.example.employeeapp.entity.PhoneNumber;
import com.example.employeeapp.repository.EmployeeRepository;
import com.example.employeeapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // EmployeeServiceImpl.java
    @Override
    public Employee saveEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setDesignation(employeeDTO.getDesignation());
        employee.setSalary(employeeDTO.getSalary());

        // Convert phone numbers from DTO to entity
        Set<PhoneNumber> phoneNumbers = new HashSet<>();
        for (String number : employeeDTO.getPhoneNumbers()) {
            PhoneNumber phoneNumber = new PhoneNumber();
            phoneNumber.setNumber(number);
            phoneNumbers.add(phoneNumber);
        }
        employee.setPhoneNumbers(phoneNumbers);

        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
}

