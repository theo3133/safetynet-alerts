package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.model.SafetyNetData;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Repository
public class FirestationRepository {
    private final ObjectMapper objectMapper;
    private final SafetyNetData safetyNetData;

    public FirestationRepository(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;

        try (InputStream inputStream = new ClassPathResource("data.json").getInputStream()) {
            this.safetyNetData = objectMapper.readValue(inputStream, SafetyNetData.class);

        } catch (IOException exception) {
            throw new IllegalStateException(
                    "Impossible de lire le fichier data.json",
                    exception
            );
        }
    }
    public List<Firestation> getFirestations() {
        return safetyNetData.getFirestations();
    }

    public Firestation addFirestation(Firestation firestation) {
        safetyNetData.getFirestations().add(firestation);
        return firestation;
    }

    public Firestation updateFirestation(Firestation firestation) {
        safetyNetData.getFirestations().remove(firestation);
        return firestation;
    }


}
