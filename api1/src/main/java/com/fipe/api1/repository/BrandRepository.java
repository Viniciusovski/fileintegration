package com.fipe.api1.repository;

import com.fipe.api1.entity.Brand;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.smallrye.mutiny.Uni;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BrandRepository implements PanacheRepository<Brand> {
    public Uni<Brand> findByName(String name) {
        return find("name", name).firstResult();
    }
}