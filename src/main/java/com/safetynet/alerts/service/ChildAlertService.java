package com.safetynet.alerts.service;

import com.safetynet.alerts.dto.Child;
import com.safetynet.alerts.dto.PersonChild;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.MedicalRecordRepository;
import com.safetynet.alerts.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ChildAlertService {

    private final MedicalRecordRepository medicalRecordRepository;
    private final PersonRepository personRepository;

    public ChildAlertService(MedicalRecordRepository medicalRecordRepository,  PersonRepository personRepository) {
        this.personRepository = personRepository;
        this.medicalRecordRepository = medicalRecordRepository;
    }


    public PersonChild childAlertAdresse(String adresse) {
        List<Person> persons = personRepository.findPersonsByAddresses(adresse);
        Map<String, Integer> ages = medicalRecordRepository.findAgesByPersons(persons);

        List<Child> children = new ArrayList<>();
        List<Person> householdMembers = new ArrayList<>(persons);

        for (Person person : persons) {
            String identity = person.firstName() + " " + person.lastName();

            Integer age = ages.get(identity);

            if (age != null && age <= 18) {
                Child child = new Child(
                        person.firstName(),
                        person.lastName(),
                        age
                );

                children.add(child);
                householdMembers.remove(person);
            }
        }

        return new PersonChild(children, householdMembers);
    }
}