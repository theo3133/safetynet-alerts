package com.safetynet.alerts.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SafetyNetData {

    private List<Person> persons;
    private List<Firestation>  firestations;
    public SafetyNetData() {
    }

    public List<Person> getPersons() {

        return persons;
    }
    public List<Firestation> getFirestations() {
        return firestations;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
    public void setFirestations(List<Firestation> firestations) {
        this.firestations = firestations;
    }
}