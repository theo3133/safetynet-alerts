package com.safetynet.alerts.service;

import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.repository.FirestationRepository;
import org.springframework.stereotype.Service;

@Service
public class FirestationService {

    private final FirestationRepository firestationRepository;

    public FirestationService(FirestationRepository firestationRepository) {
        this.firestationRepository = firestationRepository;
    }

    public Firestation addFirestation(Firestation firestation) {
        return firestationRepository.addFirestation(firestation);
    }


    public boolean addressAlreadyMapped(Firestation firestation) {
        for (Firestation currentFirestation : firestationRepository.getFirestations()) {

            boolean sameAddress = firestation.getAddress().equals(currentFirestation.getAddress());

            if (sameAddress) {
                return true;
            }
        }

        return false;
    }
}