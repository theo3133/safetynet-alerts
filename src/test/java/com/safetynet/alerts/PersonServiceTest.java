package com.safetynet.alerts;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.PersonRepository;
import com.safetynet.alerts.service.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    @Test
    void addValidPerson() {
        Person person = new Person();
        person.setFirstName("Jean");
        person.setLastName("Dupont");

        when(personRepository.getPersons()).thenReturn(List.of());
        when(personRepository.addPerson(person)).thenReturn(person);

        Person result = personService.addPerson(person);

        assertSame(person, result);
        verify(personRepository).addPerson(person);
    }

    @Test
    void rejectPersonWithBlankFirstName() {
        Person person = new Person();
        person.setFirstName("");
        person.setLastName("Dupont");

        assertThrows(IllegalArgumentException.class, () -> personService.addPerson(person));

        verify(personRepository, never()).addPerson(person);
    }

    @Test
    void rejectNullPerson() {
        assertThrows(
                IllegalArgumentException.class, () -> personService.addPerson(null));

        verifyNoInteractions(personRepository);
    }
    @Test
    void shouldRejectPersonWithSameFirstAndLastName() {
        Person existingPerson = new Person();
        existingPerson.setFirstName("Antoine");
        existingPerson.setLastName("Dupont");

        Person duplicatePerson = new Person();
        duplicatePerson.setFirstName("Antoine");
        duplicatePerson.setLastName("Dupont");

        when(personRepository.getPersons()).thenReturn(List.of(existingPerson));

        assertThrows(IllegalArgumentException.class, () -> personService.addPerson(duplicatePerson));

        verify(personRepository, never()).addPerson(duplicatePerson);
    }
}