package ir.mrmoshkel.framework;

import ir.mrmoshkel.framework.entity.AggregateRoot;
import ir.mrmoshkel.framework.event.BaseEvent;

import java.util.List;

public interface EventStore<AGG_ID> {
    void saveEvent(AggregateRoot<AGG_ID> aggregateRoot, List<BaseEvent<AGG_ID>> events, Long expectedVersion);
    List<BaseEvent<AGG_ID>> getEvents(AGG_ID aggregateId);
    List<AGG_ID> getAggregateIds();
}