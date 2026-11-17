package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.PersonService;
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
@RequestMapping("/person")
public class PersonController {

    private static final Logger logger =
            LoggerFactory.getLogger(PersonController.class);

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public Person addPerson(@Valid @RequestBody Person person) {
        logger.info("Requête entrante : POST /person");
        Person result = personService.addPerson(person);
        logger.info("Réponse sortante : POST /person - succès");
        return result;
    }

    @PutMapping
    public Person updatePerson(@Valid @RequestBody Person person) {
        logger.info("Requête entrante : PUT /person");
        Person result = personService.updatePerson(person);
        logger.info("Réponse sortante : PUT /person - succès");
        return result;
    }

    @DeleteMapping
    public boolean deletePerson(@RequestBody Person person) {
        logger.info("Requête entrante : DELETE /person");
        boolean result = personService.deletePerson(person);
        logger.info("Réponse sortante : DELETE /person - succès");
        return result;
    }
}