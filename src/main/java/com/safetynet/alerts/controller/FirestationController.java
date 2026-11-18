package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.service.FirestationService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/firestation")
public class FirestationController {

    private static final Logger logger = LoggerFactory.getLogger(FirestationController.class);

    private final FirestationService firestationService;

    public FirestationController(FirestationService firestationService) {
        this.firestationService = firestationService;
    }


    @PostMapping
    public Firestation addFirestation(@Valid @RequestBody Firestation firestation) {
        logger.info("Requête entrante : POST /firestation");
        Firestation newFirestation = firestationService.addFirestation(firestation);
        logger.info("Réponse sortante : POST /firestation - succès");
        return newFirestation;
    }
    @PutMapping
    public Firestation updateFirestation(@Valid @RequestBody Firestation firestation) {
        logger.info("Requête entrante : PUT /firestation");
        Firestation newFirestation = firestationService.updateFirestation(firestation);
        logger.info("Réponse sortante : PUT /firestation - succès");
        return newFirestation;
    }
    @DeleteMapping
    public boolean removeFirestation(@RequestBody Firestation firestation) {
        logger.info("Requête entrante : DELETE /firestation");
        boolean removed = firestationService.removeFirestation(firestation);
        logger.info("Réponse sortante : DELETE /firestation - succès");
        return removed;
    }
}
