package com.safetynet.alerts.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record MedicalRecord(
        @NotBlank(message = "Le prénom est obligatoire")
        String firstName,

        @NotBlank(message = "Le nom est obligatoire")
        String lastName,

        @NotBlank(message = "La date de naissance est obligatoire")
        String birthdate,

        @NotNull(message = "La liste des médicaments est obligatoire")
        List<String> medications,

        @NotNull(message = "La liste des allergies est obligatoire")
        List<String> allergies
) {
}
