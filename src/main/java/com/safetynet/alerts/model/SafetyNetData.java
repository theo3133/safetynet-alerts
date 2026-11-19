package com.safetynet.alerts.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SafetyNetData(
        List<Person> persons,
        List<Firestation> firestations,
        @JsonProperty("medicalrecords") List<MedicalRecord> medicalRecords
) {
}
