package ir.mrmoshkel.eventsource;

import ir.mrmoshkel.contract.EventSource;
import ir.mrmoshkel.framework.event.BaseEvent;
import ir.mrmoshkel.framework.EventProducer;
import ir.mrmoshkel.framework.EventStore;
import ir.mrmoshkel.framework.entity.AggregateRoot;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class SimpleEventSource<T extends AggregateRoot<ID>, ID> implements EventSource<T, ID> {

    private final EventStore eventStore;
    private final EventProducer eventProducer;

    public SimpleEventSource(EventStore eventStore, EventProducer eventProducer) {
        this.eventStore = eventStore;
        this.eventProducer = eventProducer;
    }

    @Override
    public T getById(ID id) {
        T aggregateRoot = (T) AggregateRoot.builder().build();
        List<BaseEvent> events = eventStore.getEvents(id);
        if (Objects.nonNull(events) && !events.isEmpty()) {
            aggregateRoot.replayEvents(events);
            Optional<Long> latestVersion = events.stream().map(BaseEvent::getVersion).max(Long::compareTo);
            aggregateRoot.setVersion(latestVersion.get());
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
            List<BaseEvent> events = eventStore.getEvents(aggregateId);
            for (BaseEvent event : events) {
                eventProducer.produce(event.getClass().getSimpleName(), event);
            }
        }
    }
}
