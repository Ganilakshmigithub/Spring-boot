package com.jwt_security_project.dtos;


public class PhoneNumberDTO {
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
