package com.fipe.api2.consumer;

import com.fipe.api2.entity.Brand;
import com.fipe.api2.entity.Vehicle;
import com.fipe.api2.repository.BrandRepository;
import com.fipe.api2.repository.VehicleRepository;
import com.fipe.api2.service.FipeService;
import io.smallrye.mutiny.Uni;
import io.smallrye.reactive.messaging.rabbitmq.IncomingRabbitMQMetadata;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class BrandConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(BrandConsumer.class);

    @Inject
    FipeService fipeService;

    @Inject
    BrandRepository brandRepository;

    @Inject
    VehicleRepository vehicleRepository;

    @Incoming("brands-in")
    public CompletionStage<Void> processBrand(Message<String> message) {
        String brand = message.getPayload();
        IncomingRabbitMQMetadata metadata = message.getMetadata(IncomingRabbitMQMetadata.class)
                .orElseThrow(() -> new IllegalArgumentException("Expected RabbitMQ metadata"));

        LOGGER.info("Processing brand: {}", brand);

        return fipeService.getModelsByBrand(brand)
                .onItem().transformToUni(models -> {
                    // Save brand first
                    return brandRepository.persist(new Brand(brand))
                            .onItem().transformToUni(b -> {
                                // Then save all vehicles
                                List<Vehicle> vehicles = models.stream()
                                        .map(model -> new Vehicle(model.getCode(), brand, model.getName()))
                                        .collect(Collectors.toList());
                                return vehicleRepository.persist(vehicles.toArray(new Vehicle[0]));
                            });
                })
                .onFailure().invoke(e -> LOGGER.error("Error processing brand: {}", brand, e))
                .subscribeAsCompletionStage()
                .thenAccept(x -> message.ack());
    }
}