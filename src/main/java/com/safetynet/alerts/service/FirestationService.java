package com.safetynet.alerts.service;

import com.safetynet.alerts.dto.PersonCouvertFirestation;
import com.safetynet.alerts.dto.PersonSimplified;
import com.safetynet.alerts.exception.DuplicateResourceException;
import com.safetynet.alerts.exception.ResourceNotFoundException;
import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.FirestationRepository;
import com.safetynet.alerts.repository.MedicalRecordRepository;
import com.safetynet.alerts.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class FirestationService {

    private final FirestationRepository firestationRepository;
    private final PersonRepository personRepository;
    private final MedicalRecordRepository medicalRecordRepository;
    public FirestationService(
            FirestationRepository firestationRepository,
            PersonRepository personRepository,
            MedicalRecordRepository medicalRecordRepository) {
        this.firestationRepository = firestationRepository;
        this.personRepository = personRepository;
        this.medicalRecordRepository = medicalRecordRepository;
    }

    public Firestation addFirestation(Firestation firestation) {
        if (!addressAlreadyMapped(firestation)) {
            return firestationRepository.addFirestation(firestation);
        }

        throw new DuplicateResourceException("L'adresse existe déjà");
    }

    public Firestation updateFirestation(Firestation firestation) {
        Firestation result =
                firestationRepository.updateFirestation(firestation);

        if (result == null) {
            throw new ResourceNotFoundException("L'adresse n'existe pas");
        }

        return result;
    }

    public boolean removeFirestation(Firestation firestation) {
        if (firestation == null) {
            throw new IllegalArgumentException("La requête est vide");
        }

        if (firestation.address() == null && firestation.station() == null) {
            throw new IllegalArgumentException(
                    "Une adresse ou un numéro de station est obligatoire"
            );
        }

        boolean removed;

        if (firestation.address() == null) {
            removed = firestationRepository
                    .removeStationFirestation(firestation.station());
        } else {
            removed = firestationRepository
                    .removeAddressFirestation(firestation.address());
        }

        if (!removed) {
            throw new ResourceNotFoundException("Aucune donnée trouvée");
        }

        return true;
    }

    private boolean addressAlreadyMapped(Firestation firestation) {
        for (Firestation currentFirestation
                : firestationRepository.getFirestations()) {

            if (firestation.address().equals(currentFirestation.address())) {
                return true;
            }
        }

        return false;
    }

    public PersonCouvertFirestation personCouvertStationfire(String numeroStation){
        int countChild = 0;
        int countAdult = 0;

        Set<String> adresses= firestationRepository.findAddressesByStation(numeroStation);
        List<Person> persons = personRepository.findPersonsByAddresses(adresses);

        List<PersonSimplified> simplifiedPersons = new ArrayList<>();
        for (Person person : persons) {
            PersonSimplified simplifiedPerson = new PersonSimplified(
                            person.firstName(),
                            person.lastName(),
                            person.address(),
                            person.phone()
                    );

            simplifiedPersons.add(simplifiedPerson);
        }

        Map<String, Integer> personAges = medicalRecordRepository.findAgesByPersons(persons);
        for (int age : personAges.values()) {
            if (age <= 18) {
                countChild++;
            } else {
                countAdult++;
            }
        }
        return new PersonCouvertFirestation(simplifiedPersons,  countChild, countAdult);
    }
}
