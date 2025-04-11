package com.fipe.api1.dto;

public class VehicleDTO {
    private String code;
    private String brand;
    private String model;
    private String observations;

    public VehicleDTO() {
    }

    public VehicleDTO(String code, String brand, String model, String observations) {
        this.code = code;
        this.brand = brand;
        this.model = model;
        this.observations = observations;
    }

    // Getters and Setters
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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