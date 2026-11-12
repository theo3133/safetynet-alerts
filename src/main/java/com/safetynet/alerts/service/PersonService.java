package com.safetynet.alerts.service;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.PersonRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person addPerson(Person person) {
        if (person == null) {
            throw new IllegalArgumentException(
                    "La personne ne peut pas être null"
            );
        }
        if (person.getFirstName() == null
                || person.getFirstName().isBlank()
                || person.getLastName() == null
                || person.getLastName().isBlank()) {

            throw new IllegalArgumentException(
                    "Le prénom et le nom sont obligatoires"
            );
        }
        if (personExists(person)) {
            throw new IllegalArgumentException(
                    "La personne existe déjà"
            );
        }
        return personRepository.addPerson(person);
    }

    public boolean personExists(Person person) {
        for (Person existingPerson : personRepository.getPersons()) {
            boolean sameFirstName = existingPerson.getFirstName().equals(person.getFirstName());
            boolean sameLastName = existingPerson.getLastName().equals(person.getLastName());
            if (sameFirstName && sameLastName) {
                return true;
            }
        }

        return false;
    }

    public Person updatePerson(Person person) {
        if (person == null) {
            throw new IllegalArgumentException("Tous les champs doivent être remplis");
        }
        if (person.getFirstName() == null
                || person.getFirstName().isBlank()
                || person.getLastName() == null
                || person.getLastName().isBlank()) {

            throw new IllegalArgumentException(
                    "Le prénom et le nom sont obligatoires"
            );
        }

        Person updatePerson = personRepository.updatePerson(person);
        if (updatePerson == null) {
            throw new IllegalArgumentException("aucune personne trouvé");
        }
        return updatePerson;
    }
}