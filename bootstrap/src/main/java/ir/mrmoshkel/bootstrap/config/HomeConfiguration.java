package ir.mrmoshkel.bootstrap.config;

import ir.mrmoshkel.contract.EventSource;
import ir.mrmoshkel.contract.IdGenerator;
import ir.mrmoshkel.eventsource.SimpleEventSource;
import ir.mrmoshkel.eventstore.BaseEventStoreRepository;
import ir.mrmoshkel.eventstore.SimpleEventStore;
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
    public HomeCommandHandler homeCommandHandler(EventSource<Home,UUID> eventSource, IdGenerator<UUID> idGenerator) {
        return new HomeCommandHandler(eventSource, idGenerator);
    }

    @Bean
    public IdGenerator<UUID> uuidGenerator() {
        return new UUIDGenerator();
    }

    @Bean
    public EventSource<Home,UUID> homeEventSource(EventStore<UUID> eventStore, EventProducer eventProducer) {
        return new SimpleEventSource<Home, UUID>(eventStore, eventProducer, () -> Home.builder().build());
    }

    @Bean
    public HomeEventHandler homeEventHandler(HomeRepositoryAdapter homeRepositoryAdapter) {
        return new HomeEventHandler(homeRepositoryAdapter);
    }

    @Bean
    public EventStore<UUID> eventStore(BaseEventStoreRepository<UUID> baseEventStoreRepository, EventProducer eventProducer) {
        return new SimpleEventStore<UUID>(baseEventStoreRepository, eventProducer);
    }


}
