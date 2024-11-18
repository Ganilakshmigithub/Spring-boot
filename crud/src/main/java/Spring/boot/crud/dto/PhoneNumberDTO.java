package Spring.boot.crud.dto;

public class PhoneNumberDTO {

    private Long id;
    private String phoneNumber;

    // Constructors
    public PhoneNumberDTO() {
    }

    public PhoneNumberDTO(Long id, String phoneNumber) {
        this.id = id;
        this.phoneNumber = phoneNumber;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
