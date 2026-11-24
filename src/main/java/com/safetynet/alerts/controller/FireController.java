package com.safetynet.alerts.controller;

import com.safetynet.alerts.dto.Fire;
import com.safetynet.alerts.service.FireService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/fire")
public class FireController {

    private static final Logger logger = LoggerFactory.getLogger(FireController.class);
    private final FireService fireService;

    public FireController(FireService fireService) {
        this.fireService = fireService;
    }

    @GetMapping
    public List<Fire> getResidentsByAddress(
            @RequestParam("address") String address) {
        logger.info("Requête entrante : GET /fire?address={}", address);
        List<Fire> residents = fireService.listeHabitant(address);
        logger.info("Réponse sortante : GET /fire?address={} - succès", address);

        return residents;
    }
}
