package com.jwt_security_project.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PhoneNumbers{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String phoneNumber;
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}