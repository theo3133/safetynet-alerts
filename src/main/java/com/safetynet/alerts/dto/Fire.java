package com.safetynet.alerts.dto;

import java.util.List;

public record Fire(
        String firstName,
        String lastName,
        String numeroStation,
        String numeroPhone,
        Integer age,
        List<String> medication,
        List<String> allergie
) {
}
