package com.jwt_security_project.service;

import com.jwt_security_project.dtos.EmployeeDTO;
import com.jwt_security_project.dtos.PhoneNumberDTO;
import com.jwt_security_project.model.employee;
import com.jwt_security_project.model.PhoneNumbers;
import com.jwt_security_project.repository.EmpRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmpService {

    @Autowired
    EmpRepo empRepo;

    // Converts EmployeeDTO to employee entity
    private employee convertToEntity(EmployeeDTO employeeDTO) {
        employee emp = new employee();
        emp.setId(employeeDTO.getId());
        emp.setName(employeeDTO.getName());
        emp.setAge(employeeDTO.getAge());

        Set<PhoneNumbers> phoneNumbers = employeeDTO.getPhoneNumbers().stream()
                .map(phoneNumberDTO -> {
                    PhoneNumbers phoneNumber = new PhoneNumbers();
                    phoneNumber.setId(phoneNumberDTO.getId());
                    phoneNumber.setPhoneNumber(phoneNumberDTO.getPhoneNumber());
                    return phoneNumber;
                }).collect(Collectors.toSet());

        emp.setPhoneNumbers(phoneNumbers);
        return emp;
    }

    // Converts employee entity to EmployeeDTO
    private EmployeeDTO convertToDTO(employee emp) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(emp.getId());
        employeeDTO.setName(emp.getName());
        employeeDTO.setAge(emp.getAge());

        Set<PhoneNumberDTO> phoneNumbers = emp.getPhoneNumbers().stream()
                .map(phoneNumber -> {
                    PhoneNumberDTO phoneNumberDTO = new PhoneNumberDTO();
                    phoneNumberDTO.setId(phoneNumber.getId());
                    phoneNumberDTO.setPhoneNumber(phoneNumber.getPhoneNumber());
                    return phoneNumberDTO;
                }).collect(Collectors.toSet());

        employeeDTO.setPhoneNumbers(phoneNumbers);
        return employeeDTO;
    }

    public EmployeeDTO addEmployee(EmployeeDTO employeeDTO) {
        // Convert DTO to entity
        employee emp = convertToEntity(employeeDTO);
        employee savedEmployee = empRepo.save(emp);

        // Convert saved entity back to DTO
        return convertToDTO(savedEmployee);
    }

    public List<EmployeeDTO> getEmployees() {
        // Get list of employees from repository
        List<employee> employees = empRepo.findAll();

        // Convert list of entities to list of DTOs
        return employees.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}
