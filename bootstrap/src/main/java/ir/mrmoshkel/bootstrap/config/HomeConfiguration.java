package ir.mrmoshkel.bootstrap.config;

import ir.mrmoshkel.contract.EventSource;
import ir.mrmoshkel.contract.IdGenerator;
import ir.mrmoshkel.eventproducer.KafkaEventProducer;
import ir.mrmoshkel.eventsource.SimpleEventSource;
import ir.mrmoshkel.eventstore.EventStoreRepository;
import ir.mrmoshkel.eventstore.SimpleEventStore;
import ir.mrmoshkel.framework.EventHandler;
import ir.mrmoshkel.framework.EventProducer;
import ir.mrmoshkel.framework.EventStore;
import ir.mrmoshkel.home.entity.Home;
import ir.mrmoshkel.home.handler.command.home.command.handler.HomeCommandHandler;
import ir.mrmoshkel.home.handler.event.HomeEventHandler;
import ir.mrmoshkel.home.handler.query.HomeQueryHandler;
import ir.mrmoshkel.idgenerator.UUIDGenerator;
import ir.mrmoshkel.persistence.home.repository.HomeRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class HomeConfiguration {
    @Bean
    public HomeQueryHandler homeQueryHandler(HomeRepositoryAdapter homeRepositoryAdapter) {
        return new HomeQueryHandler(homeRepositoryAdapter);
    }

    @Bean
    public HomeCommandHandler homeCommandHandler(EventSource<Home,Long> eventSource, IdGenerator<UUID> idGenerator) {
        return new HomeCommandHandler(eventSource, idGenerator);
    }

    @Bean
    public IdGenerator<UUID> uuidGenerator() {
        return new UUIDGenerator();
    }

    @Bean
    public EventSource<Home,Long> homeEventSource(EventStore eventStore, EventProducer eventProducer) {
        return new SimpleEventSource<>(eventStore, eventProducer);
    }

    @Bean
    public HomeEventHandler homeEventHandler(HomeRepositoryAdapter homeRepositoryAdapter) {
        return new HomeEventHandler(homeRepositoryAdapter);
    }

    @Bean
    public EventStore eventStore(EventStoreRepository eventStoreRepository, EventProducer eventProducer) {
        return new SimpleEventStore(eventStoreRepository, eventProducer);
    }


}
