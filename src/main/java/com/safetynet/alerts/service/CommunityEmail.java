package com.safetynet.alerts.service;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class CommunityEmail {
    private PersonRepository personRepository;
    public CommunityEmail(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<String> mailCity (String city) {
        List<Person> listPerson= personRepository.getPersons();
        List<String> listMailCity = new ArrayList<>();
        for (Person person : listPerson) {
            if (person.city().equalsIgnoreCase(city)) {
                listMailCity.add(person.email());
            }
        }
        return listMailCity;
    }
}
