package com.fipe.api1.dto;

public class VehicleUpdateDTO {
    private String model;
    private String observations;

    public VehicleUpdateDTO() {
    }

    public VehicleUpdateDTO(String model, String observations) {
        this.model = model;
        this.observations = observations;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }
}