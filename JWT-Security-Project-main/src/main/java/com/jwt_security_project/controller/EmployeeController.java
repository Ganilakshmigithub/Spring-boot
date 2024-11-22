package com.jwt_security_project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jwt_security_project.dtos.EmployeeDTO;
import com.jwt_security_project.service.EmpService;

@RestController
public class EmployeeController {

    @Autowired
    EmpService es;

    @GetMapping("/admin/dashboard")
    public String adminDashboard() {
        return "Welcome to the Admin Dashboard!";
    }
 
    @PostMapping("/admin/create")
    public EmployeeDTO createEmp(@RequestBody EmployeeDTO emp){
        return es.addEmployee(emp);
    }


    @GetMapping("/user/get")
    public List<EmployeeDTO> getAllEmp(){
        return es.getEmployees();
    }

    @GetMapping("/user/dashboard")
    public String userDashboard() {
        return "Welcome to the User Dashboard!";
    }
}
