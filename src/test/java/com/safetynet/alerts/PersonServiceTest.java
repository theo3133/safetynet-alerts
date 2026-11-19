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
        Person person = new Person(
                "Jean",
                "Dupont",
                "10 rue de Paris",
                "Paris",
                "75000",
                "0102030405",
                "jean.dupont@test.fr"
        );

        when(personRepository.getPersons()).thenReturn(List.of());
        when(personRepository.addPerson(person)).thenReturn(person);

        Person result = personService.addPerson(person);

        assertSame(person, result);
        verify(personRepository).addPerson(person);
    }

    @Test
    void shouldRejectPersonWithSameFirstAndLastName() {
        Person existingPerson = new Person(
                "Antoine",
                "Dupont",
                "1 rue A",
                "Paris",
                "75000",
                "0102030405",
                "antoine.dupont@test.fr"
        );

        Person duplicatePerson = new Person(
                "Antoine",
                "Dupont",
                "2 rue B",
                "Lyon",
                "69000",
                "0605040302",
                "autre@test.fr"
        );

        when(personRepository.getPersons()).thenReturn(List.of(existingPerson));

        assertThrows(IllegalArgumentException.class, () -> personService.addPerson(duplicatePerson));

        verify(personRepository, never()).addPerson(duplicatePerson);
    }
}
