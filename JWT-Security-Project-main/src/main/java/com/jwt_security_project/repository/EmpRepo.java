package com.jwt_security_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jwt_security_project.model.employee;

public interface EmpRepo extends JpaRepository<employee,Integer> {
    
}
