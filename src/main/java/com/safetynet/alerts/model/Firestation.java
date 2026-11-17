package com.safetynet.alerts.model;

import jakarta.validation.constraints.NotBlank;

public class Firestation {

    @NotBlank(message = "L'adresse est obligatoire")
    private String address;
    @NotBlank(message = "Le numéro de caserne est obligatoire")
    private String station;

    public Firestation() {
    }
    public Firestation(String address, String station) {
        this.address = address;
        this.station = station;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }
}