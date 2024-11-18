package Spring.boot.crud.dto;

import java.util.Set;

public class EmployeeDTO {

    private int id;
    private String name;
    private Set<PhoneNumberDTO> phoneNumbers;

    // Constructors
    public EmployeeDTO() {
    }

    public EmployeeDTO(int id, String name, Set<PhoneNumberDTO> phoneNumbers) {
        this.id = id;
        this.name = name;
        this.phoneNumbers = phoneNumbers;
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
}
