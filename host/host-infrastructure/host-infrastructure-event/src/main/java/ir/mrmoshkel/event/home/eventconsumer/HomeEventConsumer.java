package ir.mrmoshkel.event.home.eventconsumer;

import ir.mrmoshkel.framework.EventConsumer;
import ir.mrmoshkel.home.event.HomeCreatedEvent;
import ir.mrmoshkel.home.event.HomeDeletedEvent;
import ir.mrmoshkel.home.event.HomeReservedEvent;
import ir.mrmoshkel.home.event.HomeUpdatedEvent;
import ir.mrmoshkel.home.handler.event.HomeEventHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class HomeEventConsumer implements EventConsumer {
    private final HomeEventHandler homeEventHandler;

    public HomeEventConsumer(HomeEventHandler homeEventHandler) {
        this.homeEventHandler = homeEventHandler;
    }

    @KafkaListener(topics = "HomeCreatedEvent", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(HomeCreatedEvent homeCreatedEvent) {
        homeEventHandler.handle(homeCreatedEvent);
    }

    @KafkaListener(topics = "HomeUpdatedEvent", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(@Payload HomeUpdatedEvent homeUpdatedEvent) {
        homeEventHandler.handle(homeUpdatedEvent);
    }

    @KafkaListener(topics = "HomeDeletedEvent", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(@Payload HomeDeletedEvent homeDeletedEvent) {
        homeEventHandler.handle(homeDeletedEvent);
    }

    @KafkaListener(topics = "HomeReservedEvent", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(@Payload HomeReservedEvent homeReservedEvent) {
        homeEventHandler.handle(homeReservedEvent);
    }
}
