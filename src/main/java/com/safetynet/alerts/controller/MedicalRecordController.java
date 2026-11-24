package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.service.MedicalRecordService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicalRecord")
public class MedicalRecordController {

    private static final Logger logger =
            LoggerFactory.getLogger(MedicalRecordController.class);

    private final MedicalRecordService medicalRecordService;

    public MedicalRecordController(MedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }

    @PostMapping
    public MedicalRecord addMedicalRecord(
            @Valid @RequestBody MedicalRecord medicalRecord) {

        logger.info("Requête entrante : POST /medicalRecord");
        MedicalRecord result =
                medicalRecordService.addMedicalRecord(medicalRecord);
        logger.info("Réponse sortante : POST /medicalRecord - succès");
        return result;
    }

    @PutMapping
    public MedicalRecord updateMedicalRecord(
            @Valid @RequestBody MedicalRecord medicalRecord) {

        logger.info("Requête entrante : PUT /medicalRecord");
        MedicalRecord result =
                medicalRecordService.updateMedicalRecord(medicalRecord);
        logger.info("Réponse sortante : PUT /medicalRecord - succès");
        return result;
    }

    @DeleteMapping
    public boolean deleteMedicalRecord(
            @RequestBody MedicalRecord medicalRecord) {

        logger.info("Requête entrante : DELETE /medicalRecord");
        boolean result =
                medicalRecordService.deleteMedicalRecord(medicalRecord);
        logger.info("Réponse sortante : DELETE /medicalRecord - succès");
        return result;
    }
}
