package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.model.SafetyNetData;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Repository
public class PersonRepository {

    private final ObjectMapper objectMapper;
    private final SafetyNetData safetyNetData;

    public PersonRepository(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;

        try
                (InputStream inputStream = new ClassPathResource("data.json").getInputStream()) {
                this.safetyNetData = objectMapper.readValue(inputStream, SafetyNetData.class);

        } catch (IOException exception) {
            throw new IllegalStateException(
                    "Impossible de lire le fichier data.json",
                    exception
            );
        }
    }
    public List<Person> getPersons() {
        return safetyNetData.getPersons();
    }
    public Person addPerson(Person person) {
        safetyNetData.getPersons().add(person);
        return person;
    }
    public Person updatePerson(Person updatedPerson) {
        for (Person existingPerson : safetyNetData.getPersons()) {

            boolean sameFirstName = existingPerson.getFirstName().equals(updatedPerson.getFirstName());

            boolean sameLastName = existingPerson.getLastName().equals(updatedPerson.getLastName());

            if (sameFirstName && sameLastName) {
                existingPerson.setAddress(updatedPerson.getAddress());
                existingPerson.setCity(updatedPerson.getCity());
                existingPerson.setZip(updatedPerson.getZip());
                existingPerson.setPhone(updatedPerson.getPhone());
                existingPerson.setEmail(updatedPerson.getEmail());

                return existingPerson;
            }
        }
        return null;
    }
    public boolean deletePerson(Person person) {
        for (Person existingPerson : safetyNetData.getPersons()) {
            boolean sameFirstName = existingPerson.getFirstName().equals(person.getFirstName());
            boolean sameLastName = existingPerson.getLastName().equals(person.getLastName());

            if (sameFirstName && sameLastName) {
                safetyNetData.getPersons().remove(existingPerson);
                return true;
            }
        }
        return false;

    }
}