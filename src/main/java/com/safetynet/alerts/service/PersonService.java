package com.safetynet.alerts.service;

import com.safetynet.alerts.exception.DuplicateResourceException;
import com.safetynet.alerts.exception.ResourceNotFoundException;
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
        if (personExists(person)) {
            throw new DuplicateResourceException("La personne existe déjà");
        }

        return personRepository.addPerson(person);
    }

    public Person updatePerson(Person person) {
        Person updatedPerson = personRepository.updatePerson(person);

        if (updatedPerson == null) {
            throw new ResourceNotFoundException("Aucune personne trouvée");
        }

        return updatedPerson;
    }

    public boolean deletePerson(Person person) {
        validateIdentityForDeletion(person);

        boolean deleted = personRepository.deletePerson(person);

        if (!deleted) {
            throw new ResourceNotFoundException("Aucune personne trouvée");
        }

        return true;
    }

    private boolean personExists(Person person) {
        for (Person existingPerson : personRepository.getPersons()) {
            boolean sameFirstName = existingPerson.firstName()
                    .equals(person.firstName());
            boolean sameLastName = existingPerson.lastName()
                    .equals(person.lastName());

            if (sameFirstName && sameLastName) {
                return true;
            }
        }

        return false;
    }

    private void validateIdentityForDeletion(Person person) {
        if (person == null) {
            throw new IllegalArgumentException(
                    "La personne ne peut pas être nulle"
            );
        }

        if (person.firstName() == null
                || person.firstName().isBlank()
                || person.lastName() == null
                || person.lastName().isBlank()) {

            throw new IllegalArgumentException(
                    "Le prénom et le nom sont obligatoires"
            );
        }
    }
}
