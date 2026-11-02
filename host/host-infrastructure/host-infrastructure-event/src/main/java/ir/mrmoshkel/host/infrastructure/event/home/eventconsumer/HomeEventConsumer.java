package ir.mrmoshkel.host.infrastructure.event.home.eventconsumer;

import ir.mrmoshkel.common.infrastructure.framework.EventConsumer;
import ir.mrmoshkel.host.core.domain.home.event.HomeCreatedEvent;
import ir.mrmoshkel.host.core.domain.home.event.HomeDeletedEvent;
import ir.mrmoshkel.host.core.domain.home.event.HomeReservedEvent;
import ir.mrmoshkel.host.core.domain.home.event.HomeUpdatedEvent;
import ir.mrmoshkel.host.core.query.home.handler.event.HomeEventHandlerImpl;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class HomeEventConsumer implements EventConsumer {
    private final HomeEventHandlerImpl homeEventHandlerImpl;

    public HomeEventConsumer(HomeEventHandlerImpl homeEventHandlerImpl) {
        this.homeEventHandlerImpl = homeEventHandlerImpl;
    }

    @KafkaListener(topics = "HomeCreatedEvent", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(HomeCreatedEvent homeCreatedEvent) {
        homeEventHandlerImpl.handle(homeCreatedEvent);
    }


    @KafkaListener(topics = "HomeUpdatedEvent", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(@Payload HomeUpdatedEvent homeUpdatedEvent) {
        homeEventHandlerImpl.handle(homeUpdatedEvent);
    }

    @KafkaListener(topics = "HomeDeletedEvent", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(@Payload HomeDeletedEvent homeDeletedEvent) {
        homeEventHandlerImpl.handle(homeDeletedEvent);
    }

    @KafkaListener(topics = "HomeReservedEvent", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(@Payload HomeReservedEvent homeReservedEvent) {
        homeEventHandlerImpl.handle(homeReservedEvent);
    }
}
