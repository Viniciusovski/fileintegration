package com.fipe.api1.queue;

import io.smallrye.mutiny.Uni;
import io.smallrye.reactive.messaging.rabbitmq.OutgoingRabbitMQMetadata;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Message;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class BrandProducer {

    @Channel("brands-out")
    Emitter<String> emitter;

    public void sendBrandToQueue(String brand) {
        OutgoingRabbitMQMetadata metadata = new OutgoingRabbitMQMetadata.Builder()
                .withRoutingKey("brands")
                .build();

        emitter.send(Message.of(brand)
                .addMetadata(metadata));
    }
}