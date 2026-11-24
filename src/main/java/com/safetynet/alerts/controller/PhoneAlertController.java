package com.safetynet.alerts.controller;

import com.safetynet.alerts.service.PhoneAlertService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/phoneAlert")
public class PhoneAlertController {
    private static final Logger logger = LoggerFactory.getLogger(PhoneAlertController.class);
    private final PhoneAlertService phoneAlertService;

    public PhoneAlertController(PhoneAlertService phoneAlertService) {

        this.phoneAlertService = phoneAlertService;
    }

    @GetMapping
    public Set<String> getPhoneAlertService(@RequestParam("firestation") String firestation) {
        logger.info("Requête entrante : GET /phoneAlert?firestation={}", firestation);
        Set<String> resultat=  phoneAlertService.phonePerson(firestation);
        logger.info("Réponse sortante : GET /phoneAlert?firestation={} - succès", firestation);
        return resultat;
    }


}
