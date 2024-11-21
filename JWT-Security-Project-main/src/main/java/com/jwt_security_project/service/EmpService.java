package com.jwt_security_project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwt_security_project.model.employee;
import com.jwt_security_project.repository.EmpRepo;

@Service
public class EmpService {

    @Autowired
    EmpRepo empRepo;

    public employee addEmployee(employee emp) {
        return empRepo.save(emp);
    }

    public List<employee> getEmployee() {
        return empRepo.findAll();
    
}
}
