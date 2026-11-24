package com.safetynet.alerts.service;

import com.safetynet.alerts.dto.Fire;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.FirestationRepository;
import com.safetynet.alerts.repository.MedicalRecordRepository;
import com.safetynet.alerts.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class FireService {
    private MedicalRecordRepository medicalRecordRepository;
    private FirestationRepository firestationRepository;
    private PersonRepository personRepository;

    public FireService(MedicalRecordRepository medicalRecordRepository, FirestationRepository firestationRepository, PersonRepository personRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
        this.firestationRepository = firestationRepository;
        this.personRepository = personRepository;
    }

    public List<Fire> listeHabitant(String adresse){
        List<Fire> habitants = new ArrayList<>();
        List<Person> listPerson = personRepository.findPersonsByAddresses(adresse);
        Map<String, Integer> ages = medicalRecordRepository.findAgesByPersons(listPerson);
        String numeroStation = firestationRepository.findStationByAddress(adresse);

        for (Person person : listPerson) {
            String firstName = person.firstName();
            String lastName = person.lastName();
            String identity = firstName + " " + lastName;
            Integer age = ages.get(identity);

            String number = person.phone();
            List<String> medication = medicalRecordRepository.findMedicationsByPerson(person);
            List<String> allergie = medicalRecordRepository.findAllergieByPerson(person);

            habitants.add(new Fire(firstName,lastName,numeroStation,number,age,medication,allergie));
        }
        return habitants;
    }



}
