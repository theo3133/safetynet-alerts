package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.SafetyNetData;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Repository
public class MedicalRecordRepository {

    private final SafetyNetData safetyNetData;

    public MedicalRecordRepository(ObjectMapper objectMapper) {
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

    public List<MedicalRecord> getMedicalRecords() {
        return safetyNetData.medicalRecords();
    }

    public MedicalRecord addMedicalRecord(MedicalRecord medicalRecord) {
        safetyNetData.medicalRecords().add(medicalRecord);
        return medicalRecord;
    }

    public MedicalRecord updateMedicalRecord(MedicalRecord updatedMedicalRecord) {
        for (int index = 0;
             index < safetyNetData.medicalRecords().size();
             index++) {

            MedicalRecord existingMedicalRecord =
                    safetyNetData.medicalRecords().get(index);

            boolean sameFirstName = existingMedicalRecord.firstName()
                    .equals(updatedMedicalRecord.firstName());
            boolean sameLastName = existingMedicalRecord.lastName()
                    .equals(updatedMedicalRecord.lastName());

            if (sameFirstName && sameLastName) {
                safetyNetData.medicalRecords().set(index, updatedMedicalRecord);
                return updatedMedicalRecord;
            }
        }

        return null;
    }

    public boolean deleteMedicalRecord(MedicalRecord medicalRecord) {
        return safetyNetData.medicalRecords().removeIf(existingMedicalRecord ->
                existingMedicalRecord.firstName()
                        .equals(medicalRecord.firstName())
                        && existingMedicalRecord.lastName()
                        .equals(medicalRecord.lastName()));
    }
}
