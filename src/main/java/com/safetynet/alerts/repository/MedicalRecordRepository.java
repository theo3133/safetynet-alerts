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

    private final ObjectMapper objectMapper;
    private final SafetyNetData safetyNetData;

    public MedicalRecordRepository(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;

        try
                (InputStream inputStream = new ClassPathResource("data.json").getInputStream()) {
            this.safetyNetData = objectMapper.readValue(inputStream, SafetyNetData.class);

        } catch (IOException exception) {
            throw new IllegalStateException(
                    "Impossible de lire le fichier data.json",
                    exception);
        }
    }

    public List<MedicalRecord> getMedicalRecord() {
        return safetyNetData.getMedicalRecords();
    }

    public MedicalRecord addMedicalRecord(MedicalRecord medicalRecord) {
        getMedicalRecord().add(medicalRecord);
        return medicalRecord;
    }

    public MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord) {
        for (MedicalRecord mr : getMedicalRecord()) {
            boolean sameFirstName = mr.getFirstName().equals(medicalRecord.getFirstName());
            boolean sameLastName = mr.getLastName().equals(medicalRecord.getLastName());
            if (sameFirstName && sameLastName) {
                mr.setAllergies(medicalRecord.getAllergies());
                mr.setBirthdate(medicalRecord.getBirthdate());
                mr.setMedications(medicalRecord.getMedications());
                return mr;
            }
        }
        return null;
    }
    public boolean deleteMedicalRecord(MedicalRecord medicalRecord) {
        return getMedicalRecord().removeIf(mr -> mr.getFirstName().equals(medicalRecord.getFirstName()) && mr.getLastName().equals(medicalRecord.getLastName())
        );
    }
}

