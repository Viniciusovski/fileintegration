package com.fipe.api1.service;

import com.fipe.api1.dto.BrandDTO;
import com.fipe.api1.dto.VehicleDTO;
import com.fipe.api1.dto.VehicleUpdateDTO;
import com.fipe.api1.queue.BrandProducer;
import com.fipe.api1.repository.BrandRepository;
import com.fipe.api1.repository.VehicleRepository;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class FipeService {

    @Inject
    @RestClient
    FipeClient fipeClient;

    @Inject
    BrandProducer brandProducer;

    @Inject
    BrandRepository brandRepository;

    @Inject
    VehicleRepository vehicleRepository;

    public Uni<Void> loadInitialData() {
        return fipeClient.getBrands()
                .onItem().transformToUni(brands -> {
                    brands.forEach(brandProducer::sendBrandToQueue);
                    return Uni.createFrom().voidItem();
                });
    }

    public Uni<List<BrandDTO>> getAllBrands() {
        return brandRepository.listAll()
                .onItem().transform(brands -> brands.stream()
                        .map(brand -> new BrandDTO(brand.getName()))
                        .collect(Collectors.toList()));
    }

    public Uni<List<VehicleDTO>> getVehiclesByBrand(String brand) {
        return vehicleRepository.findByBrand(brand)
                .onItem().transform(vehicles -> vehicles.stream()
                        .map(vehicle -> new VehicleDTO(
                                vehicle.getCode(),
                                vehicle.getBrand(),
                                vehicle.getModel(),
                                vehicle.getObservations()))
                        .collect(Collectors.toList()));
    }

    public Uni<VehicleDTO> updateVehicle(Long id, VehicleUpdateDTO vehicleUpdateDTO) {
        return vehicleRepository.findById(id)
                .onItem().ifNotNull().transformToUni(vehicle -> {
                    vehicle.setModel(vehicleUpdateDTO.getModel());
                    vehicle.setObservations(vehicleUpdateDTO.getObservations());
                    return vehicleRepository.persist(vehicle)
                            .onItem().transform(v -> new VehicleDTO(
                                    v.getCode(),
                                    v.getBrand(),
                                    v.getModel(),
                                    v.getObservations()));
                });
    }
}