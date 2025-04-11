package com.fipe.api1.entity;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "brands")
public class Brand extends PanacheEntity {

    @Column(unique = true)
    public String name;

    public Brand() {
    }

    public Brand(String name) {
        this.name = name;
    }
}