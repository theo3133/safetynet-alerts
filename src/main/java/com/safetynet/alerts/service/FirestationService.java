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
        if(!addressAlreadyMapped(firestation)) {
            return firestationRepository.addFirestation(firestation);
        }
        throw new IllegalArgumentException(
                "L'adresse existe déjà"
        );
    }
    public Firestation updateFirestation(Firestation firestation) {
        Firestation result = firestationRepository.updateFirestation(firestation);

        if (result == null) {
            throw new IllegalArgumentException(
                    "L'adresse n'existe pas"
            );
        }
        return result;
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

    public boolean removeFirestation(Firestation firestation) {
        if (firestation == null) {
            throw new IllegalArgumentException("La requête est vide");
        }

        if (firestation.getAddress() == null && firestation.getStation() == null) {
            throw new IllegalArgumentException(
                    "Une adresse ou un numéro de station est obligatoire"
            );
        }

        boolean removed;

        if (firestation.getAddress() == null) {
            removed = firestationRepository.removeStationFirestation(firestation.getStation());
        } else {
            removed = firestationRepository.removeAddressFirestation(firestation.getAddress());
        }

        if (!removed) {
            throw new IllegalArgumentException("Aucune donnée trouvée");
        }

        return true;
    }
}