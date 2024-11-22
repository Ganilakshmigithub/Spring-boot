package com.jwt_security_project.dtos;

import java.util.HashSet;
import java.util.Set;


public class EmployeeDTO {
    private int id;
    String name;
    int age;
    Set<PhoneNumberDTO> phoneNumbers = new HashSet<>();
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Set<PhoneNumberDTO> getPhoneNumbers() {
        return phoneNumbers;
    }
    public void setPhoneNumbers(Set<PhoneNumberDTO> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }
    
    
}

