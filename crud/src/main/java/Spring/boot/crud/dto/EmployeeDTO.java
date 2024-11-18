package Spring.boot.crud.dto;

import java.util.Set;

public class EmployeeDTO {

    private int id;
    private String name;
    private Set<PhoneNumberDTO> phoneNumbers;
    private String address;
    private String designation;
    private int salary;

    public EmployeeDTO(int id,String name, Set<PhoneNumberDTO> phoneNumbers, String address, String designation, int salary) {
        this.id=id;
        this.name = name;
        this.phoneNumbers = phoneNumbers;
        this.address = address;
        this.designation = designation;
        this.salary = salary;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<PhoneNumberDTO> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(Set<PhoneNumberDTO> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
