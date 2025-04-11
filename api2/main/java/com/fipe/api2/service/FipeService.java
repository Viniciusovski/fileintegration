package com.fipe.api2.service;

import com.fipe.api2.dto.ModelDTO;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class FipeService {

    @Inject
    @RestClient
    FipeClient fipeClient;

    public Uni<List<ModelDTO>> getModelsByBrand(String brand) {
        return fipeClient.getModels(brand);
    }
}