package com.fipe.api2.entity;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "vehicles")
public class Vehicle extends PanacheEntity {

    public String code;

    public String brand;

    public String model;

    @Column(length = 1000)
    public String observations;

    public Vehicle() {
    }

    public Vehicle(String code, String brand, String model) {
        this.code = code;
        this.brand = brand;
        this.model = model;
    }
}