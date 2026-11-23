package com.safetynet.alerts.service;

import com.safetynet.alerts.exception.DuplicateResourceException;
import com.safetynet.alerts.exception.ResourceNotFoundException;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.repository.MedicalRecordRepository;
import org.springframework.stereotype.Service;

@Service
public class MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;

    public MedicalRecordService(
            MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
    }

    public MedicalRecord addMedicalRecord(MedicalRecord medicalRecord) {
        if (medicalRecordExists(medicalRecord)) {
            throw new DuplicateResourceException(
                    "Le dossier médical existe déjà"
            );
        }

        return medicalRecordRepository.addMedicalRecord(medicalRecord);
    }

    public MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord) {
        MedicalRecord updatedMedicalRecord =
                medicalRecordRepository.updateMedicalRecord(medicalRecord);

        if (updatedMedicalRecord == null) {
            throw new ResourceNotFoundException(
                    "Aucun dossier médical trouvé"
            );
        }

        return updatedMedicalRecord;
    }

    public boolean deleteMedicalRecord(MedicalRecord medicalRecord) {
        validateIdentityForDeletion(medicalRecord);

        boolean deleted =
                medicalRecordRepository.deleteMedicalRecord(medicalRecord);

        if (!deleted) {
            throw new ResourceNotFoundException(
                    "Aucun dossier médical trouvé"
            );
        }

        return true;
    }

    private boolean medicalRecordExists(MedicalRecord medicalRecord) {
        for (MedicalRecord existingMedicalRecord
                : medicalRecordRepository.getMedicalRecords()) {

            boolean sameFirstName = existingMedicalRecord.firstName()
                    .equals(medicalRecord.firstName());
            boolean sameLastName = existingMedicalRecord.lastName()
                    .equals(medicalRecord.lastName());

            if (sameFirstName && sameLastName) {
                return true;
            }
        }

        return false;
    }

    private void validateIdentityForDeletion(MedicalRecord medicalRecord) {
        if (medicalRecord == null) {
            throw new IllegalArgumentException(
                    "Le dossier médical ne peut pas être nul"
            );
        }

        if (medicalRecord.firstName() == null
                || medicalRecord.firstName().isBlank()
                || medicalRecord.lastName() == null
                || medicalRecord.lastName().isBlank()) {

            throw new IllegalArgumentException(
                    "Le prénom et le nom sont obligatoires"
            );
        }
    }
}
