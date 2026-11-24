package com.safetynet.alerts.service;

import com.safetynet.alerts.repository.FirestationRepository;
import com.safetynet.alerts.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class PhoneAlertService {
    private FirestationRepository firestationRepository;
    private PersonRepository personRepository;
    public PhoneAlertService(PersonRepository personRepository, FirestationRepository firestationRepository) {
        this.personRepository = personRepository;
        this.firestationRepository = firestationRepository;
    }

    public Set<String> phonePerson(String firestation){
        Set<String> adresse = firestationRepository.findAddressesByStation(firestation);
        return  personRepository.findNumberByAddresses(adresse);

    }
}
