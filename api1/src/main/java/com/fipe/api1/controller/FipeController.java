package com.fipe.api1.controller;

import com.fipe.api1.dto.BrandDTO;
import com.fipe.api1.dto.VehicleDTO;
import com.fipe.api1.dto.VehicleUpdateDTO;
import com.fipe.api1.service.FipeService;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/api/v1/fipe")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "FIPE Integration", description = "Endpoints for FIPE integration")
public class FipeController {

    private final FipeService fipeService;

    public FipeController(FipeService fipeService) {
        this.fipeService = fipeService;
    }

    @POST
    @Path("/load-initial-data")
    @Operation(summary = "Trigger initial data load from FIPE API")
    public Uni<Response> loadInitialData() {
        return fipeService.loadInitialData()
                .onItem().transform(item -> Response.accepted().build());
    }

    @GET
    @Path("/brands")
    @Operation(summary = "Get all brands from database")
    public Uni<List<BrandDTO>> getAllBrands() {
        return fipeService.getAllBrands();
    }

    @GET
    @Path("/vehicles/{brand}")
    @Operation(summary = "Get vehicles by brand")
    public Uni<List<VehicleDTO>> getVehiclesByBrand(@PathParam("brand") String brand) {
        return fipeService.getVehiclesByBrand(brand);
    }

    @PUT
    @Path("/vehicles/{id}")
    @Operation(summary = "Update vehicle details")
    public Uni<VehicleDTO> updateVehicle(
            @PathParam("id") Long id,
            VehicleUpdateDTO vehicleUpdateDTO) {
        return fipeService.updateVehicle(id, vehicleUpdateDTO);
    }
}