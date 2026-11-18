package com.safetynet.alerts.model;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public class MedicalRecord {
    @NotBlank(message = "Le prénom est obligatoire")
    private String firstName;
    @NotBlank(message = "Le nom est obligatoire")
    private String lastName;
    @NotBlank(message = "La date de naissance est obligatoire")
    private String birthdate;
    @NotNull(message = "La liste des médicaments est obligatoire")
    private List<String> medications;
    @NotNull(message = "La liste des allergies est obligatoire")
    private List<String> allergies;

    public MedicalRecord() {
    }

    public MedicalRecord(
            String firstName,
            String lastName,
            String birthdate,
            List<String> medications,
            List<String> allergies
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.medications = medications;
        this.allergies = allergies;
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

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public List<String> getMedications() {
        return medications;
    }

    public void setMedications(List<String> medications) {
        this.medications = medications;
    }

    public List<String> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<String> allergies) {
        this.allergies = allergies;
    }
}
