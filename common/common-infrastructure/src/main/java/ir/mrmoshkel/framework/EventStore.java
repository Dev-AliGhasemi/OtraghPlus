package ir.mrmoshkel.framework;

import ir.mrmoshkel.framework.entity.BaseAggregateRoot;
import ir.mrmoshkel.framework.event.BaseEvent;

import java.util.List;

public interface EventStore<AGG_ID> {
    void saveEvent(BaseAggregateRoot<AGG_ID> baseAggregateRoot, List<BaseEvent<AGG_ID>> events, Long expectedVersion);
    List<BaseEvent<AGG_ID>> getEvents(AGG_ID aggregateId);
    List<AGG_ID> getAggregateIds();
}