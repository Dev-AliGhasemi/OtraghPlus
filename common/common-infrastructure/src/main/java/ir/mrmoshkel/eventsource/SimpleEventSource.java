package ir.mrmoshkel.eventsource;

import ir.mrmoshkel.contract.EventSource;
import ir.mrmoshkel.framework.event.BaseEvent;
import ir.mrmoshkel.framework.EventProducer;
import ir.mrmoshkel.framework.EventStore;
import ir.mrmoshkel.framework.entity.AggregateRoot;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class SimpleEventSource<T extends AggregateRoot<ID>, ID> implements EventSource<T, ID> {

    private final EventStore<ID> eventStore;
    private final EventProducer eventProducer;
    private Supplier<? extends AggregateRoot<ID>> aggregateRootSupplier;

    public SimpleEventSource(EventStore<ID> eventStore, EventProducer eventProducer, Supplier<? extends AggregateRoot<ID>> supplier) {
        this.eventStore = eventStore;
        this.eventProducer = eventProducer;
        this.aggregateRootSupplier = supplier;
    }

    @Override
    public T getById(ID id) {
        T aggregateRoot = (T) aggregateRootSupplier.get();
        List<BaseEvent<ID>> events = eventStore.getEvents(id);
        if (Objects.nonNull(events) && !events.isEmpty()) {
            aggregateRoot.replayEvents(events);
            Optional<Long> latestVersion = events.stream().map(BaseEvent::getVersion).max(Long::compareTo);
            aggregateRoot.setVersion(latestVersion.get());
            aggregateRoot.setName(aggregateRoot.getName());
            aggregateRoot.setId(id);
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
        List<ID> aggregateIds = eventStore.getAggregateIds();
        for (ID aggregateId : aggregateIds) {
            T accountAggregate = getById(aggregateId);
            if (accountAggregate == null) continue;
            List<BaseEvent<ID>> events = eventStore.getEvents(aggregateId);
            for (BaseEvent<ID> event : events) {
                try {
                    eventProducer.produce(event.getClass().getSimpleName(), event);
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
