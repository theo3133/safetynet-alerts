package com.safetynet.alerts.dto;

import java.util.List;

public record PersonCouvertFirestation (
    List<PersonSimplified> personSimplified,
    int countChild,
    int countAdult
    )
{
}
