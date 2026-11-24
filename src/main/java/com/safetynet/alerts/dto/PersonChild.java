package com.safetynet.alerts.dto;

import com.safetynet.alerts.model.Person;

import java.util.List;

public record PersonChild (
        List<Child> child,
        List<Person> membreFoyer
        )
{}
