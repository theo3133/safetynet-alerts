package com.safetynet.alerts.service;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.repository.MedicalRecordRepository;
import org.springframework.stereotype.Service;

@Service
public class MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;

    public MedicalRecordService(MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
    }

    public MedicalRecord addMedicalRecord(MedicalRecord medicalRecord) {
        if (medicalRecordExists(medicalRecord)) {
            throw new IllegalArgumentException(
                    "Le dossier médical existe déjà"
            );
        }

        return medicalRecordRepository.addMedicalRecord(medicalRecord);
    }

    public MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord) {
        MedicalRecord updatedMedicalRecord =
                medicalRecordRepository.updateMedicalRecord(medicalRecord);

        if (updatedMedicalRecord == null) {
            throw new IllegalArgumentException(
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
            throw new IllegalArgumentException(
                    "Aucun dossier médical trouvé"
            );
        }

        return true;
    }

    private boolean medicalRecordExists(MedicalRecord medicalRecord) {
        for (MedicalRecord existingMedicalRecord
                : medicalRecordRepository.getMedicalRecord()) {

            boolean sameFirstName = existingMedicalRecord.getFirstName()
                    .equals(medicalRecord.getFirstName());
            boolean sameLastName = existingMedicalRecord.getLastName()
                    .equals(medicalRecord.getLastName());

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

        if (medicalRecord.getFirstName() == null
                || medicalRecord.getFirstName().isBlank()
                || medicalRecord.getLastName() == null
                || medicalRecord.getLastName().isBlank()) {

            throw new IllegalArgumentException(
                    "Le prénom et le nom sont obligatoires"
            );
        }
    }
}
