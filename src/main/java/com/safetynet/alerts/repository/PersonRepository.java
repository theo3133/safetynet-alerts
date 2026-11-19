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

    private final SafetyNetData safetyNetData;

    public PersonRepository(ObjectMapper objectMapper) {
        try (InputStream inputStream =
                     new ClassPathResource("data.json").getInputStream()) {
            this.safetyNetData =
                    objectMapper.readValue(inputStream, SafetyNetData.class);
        } catch (IOException exception) {
            throw new IllegalStateException(
                    "Impossible de lire le fichier data.json",
                    exception
            );
        }
    }

    public List<Person> getPersons() {
        return safetyNetData.persons();
    }

    public Person addPerson(Person person) {
        safetyNetData.persons().add(person);
        return person;
    }

    public Person updatePerson(Person updatedPerson) {
        for (int index = 0; index < safetyNetData.persons().size(); index++) {
            Person existingPerson = safetyNetData.persons().get(index);

            boolean sameFirstName = existingPerson.firstName().equals(updatedPerson.firstName());
            boolean sameLastName = existingPerson.lastName().equals(updatedPerson.lastName());

            if (sameFirstName && sameLastName) {
                safetyNetData.persons().set(index, updatedPerson);
                return updatedPerson;
            }
        }

        return null;
    }

    public boolean deletePerson(Person person) {
        return safetyNetData.persons().removeIf(existingPerson ->
                existingPerson.firstName().equals(person.firstName())
                        && existingPerson.lastName().equals(person.lastName()));
    }
}
