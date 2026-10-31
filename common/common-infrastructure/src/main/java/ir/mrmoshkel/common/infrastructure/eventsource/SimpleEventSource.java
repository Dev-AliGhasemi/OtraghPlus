package ir.mrmoshkel.common.infrastructure.eventsource;

import ir.mrmoshkel.contract.EventSource;
import ir.mrmoshkel.framework.event.BaseEvent;
import ir.mrmoshkel.common.infrastructure.framework.EventProducer;
import ir.mrmoshkel.common.infrastructure.framework.EventStore;
import ir.mrmoshkel.framework.entity.BaseAggregateRoot;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

public class SimpleEventSource<T extends BaseAggregateRoot<AGG_ID>, AGG_ID> implements EventSource<T, AGG_ID> {

    private final EventStore<AGG_ID> eventStore;
    private final EventProducer eventProducer;
    private Supplier<? extends BaseAggregateRoot<AGG_ID>> aggregateRootSupplier;

    public SimpleEventSource(EventStore<AGG_ID> eventStore, EventProducer eventProducer, Supplier<? extends BaseAggregateRoot<AGG_ID>> supplier) {
        this.eventStore = eventStore;
        this.eventProducer = eventProducer;
        this.aggregateRootSupplier = supplier;
    }

    @Override
    public T getById(AGG_ID AGGId) {
        T aggregateRoot = (T) aggregateRootSupplier.get();
        List<BaseEvent<AGG_ID>> events = eventStore.getEvents(AGGId);
        if (Objects.nonNull(events) && !events.isEmpty()) {
            aggregateRoot.replayEvents(events);
            Optional<Long> latestVersion = events.stream().map(BaseEvent::getVersion).max(Long::compareTo);
            aggregateRoot.postInitialize(AGGId, aggregateRoot.getName(), latestVersion.orElse(null));
        }
        return aggregateRoot;
    }

    @Override
    public void save(T t) {
        eventStore.saveEvent(t, t.getEvents(), t.getVersion());
        t.markEventsAsCommited();
    }

    @Override
    public void republishEvents() {
        List<AGG_ID> aggregateIds = eventStore.getAggregateIds();
        for (AGG_ID aggregateId : aggregateIds) {
            T accountAggregate = getById(aggregateId);
            if (accountAggregate == null) continue;
            List<BaseEvent<AGG_ID>> events = eventStore.getEvents(aggregateId);
            for (BaseEvent<AGG_ID> event : events) {
                try {
                    eventProducer.produce(event.getClass().getSimpleName(), event);
                } catch (ExecutionException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
