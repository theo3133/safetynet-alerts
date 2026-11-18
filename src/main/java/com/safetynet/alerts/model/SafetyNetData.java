package com.safetynet.alerts.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SafetyNetData {

    private List<Person> persons;
    private List<Firestation> firestations;
    @JsonProperty("medicalrecords")
    private List<MedicalRecord> medicalRecords;

    public SafetyNetData() {
    }

    public List<Person> getPersons() {
        return persons;
    }
    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }


    public List<Firestation> getFirestations() {
        return firestations;
    }
    public void setFirestations(List<Firestation> firestations) {
        this.firestations = firestations;
    }

    public List<MedicalRecord> getMedicalRecords() {
        return medicalRecords;
    }
    public void setMedicalRecords(List<MedicalRecord> medicalRecords) {
        this.medicalRecords = medicalRecords;
    }
}