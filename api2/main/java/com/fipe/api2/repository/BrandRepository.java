package com.fipe.api2.repository;

import com.fipe.api2.entity.Brand;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.smallrye.mutiny.Uni;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BrandRepository implements PanacheRepository<Brand> {
}