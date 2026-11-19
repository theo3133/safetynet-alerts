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
        if (!addressAlreadyMapped(firestation)) {
            return firestationRepository.addFirestation(firestation);
        }

        throw new IllegalArgumentException("L'adresse existe déjà");
    }

    public Firestation updateFirestation(Firestation firestation) {
        Firestation result =
                firestationRepository.updateFirestation(firestation);

        if (result == null) {
            throw new IllegalArgumentException("L'adresse n'existe pas");
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
            throw new IllegalArgumentException("Aucune donnée trouvée");
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
}
