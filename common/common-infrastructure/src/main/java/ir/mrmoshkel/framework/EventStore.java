package ir.mrmoshkel.framework;

import ir.mrmoshkel.eventstore.BaseEventStoreRepository;
import ir.mrmoshkel.framework.entity.AggregateRoot;
import ir.mrmoshkel.framework.event.BaseEvent;

import java.util.List;

public interface EventStore {
    BaseEventStoreRepository getBaseEventStoreRepository();
    <ID> void saveEvent(AggregateRoot<ID> aggregateRoot, List<BaseEvent> events, Long expectedVersion);
    <ID> List<BaseEvent> getEvents(ID aggregateId);
    <ID> List<ID> getAggregateIds();
}