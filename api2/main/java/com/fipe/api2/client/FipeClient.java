package com.fipe.api2.client;

import com.fipe.api2.dto.ModelDTO;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

@RegisterRestClient(configKey = "fipe-api")
@Path("/api/v1")
public interface FipeClient {

    @GET
    @Path("/carros/marcas/{brand}/modelos")
    List<ModelDTO> getModels(@PathParam("brand") String brand);
}