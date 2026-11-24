package com.safetynet.alerts.controller;

import com.safetynet.alerts.controller.FirestationController;
import com.safetynet.alerts.dto.PersonChild;
import com.safetynet.alerts.service.ChildAlertService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/childAlert")
public class ChildAlertController {
    private static final Logger logger = LoggerFactory.getLogger(ChildAlertController.class);

    private final ChildAlertService childAlertService;

    public ChildAlertController(ChildAlertService childAlertService) {
        this.childAlertService = childAlertService;
    }

    @GetMapping
    public PersonChild getChildrenByAddress(@RequestParam("address") String address) {
        logger.info("Requête entrante : GET /childAlert?address={}", address);
        PersonChild resultat=  childAlertService.childAlertAdresse(address);
        logger.info("Réponse sortante : GET /childAlert?address={} - succès", address);
        return resultat;
    }
}