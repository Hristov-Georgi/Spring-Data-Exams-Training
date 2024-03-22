package laptopShopExercise.model.customer;

import jakarta.validation.constraints.Email;
import laptopShopExercise.model.town.TownNameDTO;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

public class CustomerImportDTO {

    @Length(min = 2)
    private String firstName;

    @Length(min = 2)
    private String lastName;

    @Email
    private String email;

    private LocalDate registeredOn;

    private TownNameDTO town;

    public CustomerImportDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(LocalDate registeredOn) {
        this.registeredOn = registeredOn;
    }

    public TownNameDTO getTown() {
        return town;
    }

    public void setTown(TownNameDTO town) {
        this.town = town;
    }
}
