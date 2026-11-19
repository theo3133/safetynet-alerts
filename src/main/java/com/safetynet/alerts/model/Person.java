package com.safetynet.alerts.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record Person(
        @NotBlank(message = "Le prénom est obligatoire")
        String firstName,

        @NotBlank(message = "Le nom est obligatoire")
        String lastName,

        @NotBlank(message = "L'adresse est obligatoire")
        String address,

        @NotBlank(message = "La ville est obligatoire")
        String city,

        @NotBlank(message = "Le code postal est obligatoire")
        String zip,

        @NotBlank(message = "Le téléphone est obligatoire")
        String phone,

        @NotBlank(message = "L'email est obligatoire")
        @Email(message = "Le format de l'email est invalide")
        String email
) {
}
