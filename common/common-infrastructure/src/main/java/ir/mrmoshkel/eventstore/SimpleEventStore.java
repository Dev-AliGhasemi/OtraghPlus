package ir.mrmoshkel.eventstore;

import ir.mrmoshkel.framework.event.BaseEvent;
import ir.mrmoshkel.framework.BaseEventModel;
import ir.mrmoshkel.framework.EventProducer;
import ir.mrmoshkel.framework.EventStore;
import ir.mrmoshkel.framework.entity.AggregateRoot;
import ir.mrmoshkel.model.MongoEventModel;

import java.sql.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

public class SimpleEventStore implements EventStore {

    private final EventStoreRepository eventStoreRepository;
    private final EventProducer eventProducer;

    public SimpleEventStore(EventStoreRepository eventStoreRepository, EventProducer eventProducer) {
        this.eventStoreRepository = eventStoreRepository;
        this.eventProducer = eventProducer;
    }

    @Override
    public <ID> void saveEvent(AggregateRoot<ID> aggregateRoot, List<BaseEvent> events, Long expectedVersion) {
        List<MongoEventModel<ID>> mongoEventModels = eventStoreRepository.findByAggregateId(aggregateRoot.getId());
        if (Objects.isNull(mongoEventModels) || (!mongoEventModels.isEmpty() && mongoEventModels.get(mongoEventModels.size() - 1).getVersion() != expectedVersion))
            throw new RuntimeException("Concurrency error");
        Long version = expectedVersion == null ? 0 : expectedVersion;
        for (BaseEvent baseEvent : events) {
            version++;
            baseEvent.setVersion(version);
            MongoEventModel mongoEventModel = MongoEventModel.builder()
                    .timestamp(new Date(System.currentTimeMillis()))
                    .aggregateId(aggregateRoot.getId())
                    .version(version)
                    .aggregateType(aggregateRoot.getClass().getTypeName())
                    .eventType(baseEvent.getClass().getTypeName())
                    .eventData(baseEvent)
                    .build();
            MongoEventModel saved = eventStoreRepository.save(mongoEventModel);
            if (Objects.nonNull(saved.getId())) {
                try {
                    eventProducer.produce(baseEvent.getClass().getSimpleName(), baseEvent);
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    public <ID> List<BaseEvent> getEvents(ID aggregateId) {
        List<MongoEventModel<ID>> mongoEventModels = eventStoreRepository.findByAggregateId(aggregateId);
        if (Objects.nonNull(mongoEventModels) || mongoEventModels.isEmpty())
            throw new RuntimeException("No events exists");
        return mongoEventModels.stream().map(MongoEventModel::getEventData).toList();
    }

    @Override
    public <ID> List<ID> getAggregateIds() {
        List<MongoEventModel> mongoEventModels = eventStoreRepository.findAll();
        if (mongoEventModels.isEmpty())
            throw new RuntimeException("No events exists");
        return (List<ID>) mongoEventModels.stream().map(BaseEventModel::getAggregateId).distinct().toList();
    }
}
