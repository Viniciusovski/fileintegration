package com.fipe.api1.repository;

import com.fipe.api1.entity.Vehicle;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import io.smallrye.mutiny.Uni;
import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class VehicleRepository implements PanacheRepository<Vehicle> {
    public Uni<List<Vehicle>> findByBrand(String brand) {
        return list("brand", Sort.by("model"), brand);
    }
}