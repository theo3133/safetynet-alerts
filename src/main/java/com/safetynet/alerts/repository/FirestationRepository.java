package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.model.SafetyNetData;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class FirestationRepository {

    private final SafetyNetData safetyNetData;

    public FirestationRepository(ObjectMapper objectMapper) {
        try (InputStream inputStream =
                     new ClassPathResource("data.json").getInputStream()) {
            this.safetyNetData =
                    objectMapper.readValue(inputStream, SafetyNetData.class);
        } catch (IOException exception) {
            throw new IllegalStateException(
                    "Impossible de lire le fichier data.json",
                    exception
            );
        }
    }

    public List<Firestation> getFirestations() {
        return safetyNetData.firestations();
    }

    public Firestation addFirestation(Firestation firestation) {
        safetyNetData.firestations().add(firestation);
        return firestation;
    }

    public Firestation updateFirestation(Firestation updatedFirestation) {
        for (int index = 0;
             index < safetyNetData.firestations().size();
             index++) {

            Firestation existingFirestation =
                    safetyNetData.firestations().get(index);

            if (existingFirestation.address()
                    .equals(updatedFirestation.address())) {

                safetyNetData.firestations().set(index, updatedFirestation);
                return updatedFirestation;
            }
        }

        return null;
    }

    public boolean removeAddressFirestation(String address) {
        return safetyNetData.firestations().removeIf(existingFirestation ->
                existingFirestation.address().equals(address));
    }

    public boolean removeStationFirestation(String station) {
        return safetyNetData.firestations().removeIf(existingFirestation ->
                existingFirestation.station().equals(station));
    }

    public Set<String>  findAddressesByStation(String station){
        Set<String> adresses = new HashSet<>();
        for (Firestation currentFirestation : safetyNetData.firestations()) {
            if(currentFirestation.station().equals(station)){
                adresses.add(currentFirestation.address());
            }
        }
        return adresses;
    }

    public String findStationByAddress(String address){
        for (Firestation currentFirestation : safetyNetData.firestations()) {
            if(currentFirestation.address().equals(address)) {
                return currentFirestation.station();
            }
        }
        return null;
    }
}
