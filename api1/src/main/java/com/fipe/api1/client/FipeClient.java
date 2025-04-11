package com.fipe.api1.client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

@RegisterRestClient(configKey = "fipe-api")
@Path("/api/v1")
public interface FipeClient {

    @GET
    @Path("/carros/marcas")
    List<String> getBrands();
}