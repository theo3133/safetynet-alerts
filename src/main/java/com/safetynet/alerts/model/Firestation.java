package com.safetynet.alerts.model;

import jakarta.validation.constraints.NotBlank;

public record Firestation(
        @NotBlank(message = "L'adresse est obligatoire")
        String address,

        @NotBlank(message = "Le numéro de caserne est obligatoire")
        String station
) {
}
