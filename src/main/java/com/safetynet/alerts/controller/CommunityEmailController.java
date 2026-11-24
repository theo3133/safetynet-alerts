package com.safetynet.alerts.controller;

import com.safetynet.alerts.service.CommunityEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/communityEmail")
public class CommunityEmailController {

    private static final Logger logger =
            LoggerFactory.getLogger(CommunityEmailController.class);

    private final CommunityEmail communityEmail;

    public CommunityEmailController(CommunityEmail communityEmail) {
        this.communityEmail = communityEmail;
    }

    @GetMapping
    public List<String> getEmailsByCity(
            @RequestParam("city") String city
    ) {
        logger.info(
                "Requête entrante : GET /communityEmail?city={}",
                city
        );

        List<String> emails = communityEmail.mailCity(city);

        logger.info(
                "Réponse sortante : GET /communityEmail?city={} - succès",
                city
        );

        return emails;
    }
}
