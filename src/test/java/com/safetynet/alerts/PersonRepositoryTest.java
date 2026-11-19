package com.safetynet.alerts;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    void shouldLoadPersonsFromJsonFile() {
        List<Person> persons = personRepository.getPersons();

        assertNotNull(persons);
        assertFalse(persons.isEmpty());
        Person firstPerson = persons.get(0);
        assertEquals("John", firstPerson.firstName());
    }

    @Test
    void shouldAddPerson() {
        int initialSize = personRepository.getPersons().size();

        Person newPerson = new Person(
                "Test",
                "Person",
                "1 rue du Test",
                "Paris",
                "75000",
                "0102030405",
                "test.person@test.fr"
        );

        personRepository.addPerson(newPerson);

        assertEquals(
                initialSize + 1,
                personRepository.getPersons().size()
        );
    }
}
