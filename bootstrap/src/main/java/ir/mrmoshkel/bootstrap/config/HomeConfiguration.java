package ir.mrmoshkel.bootstrap.config;

import ir.mrmoshkel.common.core.contract.EventSource;
import ir.mrmoshkel.common.core.contract.IdGenerator;
import ir.mrmoshkel.common.infrastructure.eventsource.SimpleEventSource;
import ir.mrmoshkel.common.infrastructure.eventstore.BaseEventStoreRepository;
import ir.mrmoshkel.common.infrastructure.eventstore.SimpleEventStore;
import ir.mrmoshkel.common.infrastructure.framework.EventProducer;
import ir.mrmoshkel.common.infrastructure.framework.EventStore;
import ir.mrmoshkel.host.core.command.home.handler.HomeCommandHandler;
import ir.mrmoshkel.host.core.domain.home.entity.Home;
import ir.mrmoshkel.host.core.command.home.handler.HomeCommandHandlerImpl;
import ir.mrmoshkel.host.core.query.home.handler.event.HomeEventHandlerImpl;
import ir.mrmoshkel.host.core.query.home.handler.query.HomeQueryHandler;
import ir.mrmoshkel.common.infrastructure.idgenerator.UUIDGenerator;
import ir.mrmoshkel.host.core.query.home.handler.query.HomeQueryHandlerImpl;
import ir.mrmoshkel.host.core.query.home.repository.HomeRepository;
import ir.mrmoshkel.host.infrastructure.persistence.home.repository.HomeJpaRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class HomeConfiguration {
    @Bean
    public HomeQueryHandler homeQueryHandler(HomeRepository homeRepository) {
        return new HomeQueryHandlerImpl(homeRepository);
    }

    @Bean
    public HomeCommandHandler homeCommandHandler(EventSource<Home,UUID> eventSource, IdGenerator<UUID> idGenerator) {
        return new HomeCommandHandlerImpl(eventSource, idGenerator);
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
    public HomeEventHandlerImpl homeEventHandler(HomeJpaRepositoryAdapter homeRepositoryAdapter) {
        return new HomeEventHandlerImpl(homeRepositoryAdapter);
    }

    @Bean
    public EventStore<UUID> eventStore(BaseEventStoreRepository<UUID> baseEventStoreRepository, EventProducer eventProducer) {
        return new SimpleEventStore<UUID>(baseEventStoreRepository, eventProducer);
    }


}
