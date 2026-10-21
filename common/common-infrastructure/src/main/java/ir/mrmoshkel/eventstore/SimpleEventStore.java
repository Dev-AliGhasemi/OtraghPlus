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

public class SimpleEventStore<AGG_ID> implements EventStore<AGG_ID> {

    private final BaseEventStoreRepository baseEventStoreRepository;
    private final EventProducer eventProducer;

    public SimpleEventStore(BaseEventStoreRepository baseEventStoreRepository, EventProducer eventProducer) {
        this.baseEventStoreRepository = baseEventStoreRepository;
        this.eventProducer = eventProducer;
    }

    @Override
    public void saveEvent(AggregateRoot<AGG_ID> aggregateRoot, List<BaseEvent<AGG_ID>> events, Long expectedVersion) {
        List<MongoEventModel<AGG_ID>> mongoEventModels = baseEventStoreRepository.findByAggregateId(aggregateRoot.getId());
        if (Objects.isNull(mongoEventModels) || (!mongoEventModels.isEmpty() && mongoEventModels.get(mongoEventModels.size() - 1).getVersion() != expectedVersion))
            throw new RuntimeException("Concurrency error");
        Long version = expectedVersion == null ? 0 : expectedVersion;
        for (BaseEvent<AGG_ID> baseEvent : events) {
            version++;
            baseEvent.setVersion(version);
            MongoEventModel<AGG_ID> mongoEventModel = MongoEventModel.<AGG_ID>builder()
                    .timestamp(new Date(System.currentTimeMillis()))
                    .aggregateId(aggregateRoot.getId())
                    .version(version)
                    .aggregateType(aggregateRoot.getClass().getTypeName())
                    .eventType(baseEvent.getClass().getTypeName())
                    .eventData(baseEvent)
                    .build();
            MongoEventModel saved = (MongoEventModel) baseEventStoreRepository.save(mongoEventModel);
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
    public List<BaseEvent<AGG_ID>> getEvents(AGG_ID aggregateId) {
        List<MongoEventModel<AGG_ID>> mongoEventModels = baseEventStoreRepository.findByAggregateId(aggregateId);
        if (Objects.nonNull(mongoEventModels) || mongoEventModels.isEmpty())
            throw new RuntimeException("No events exists");
        return mongoEventModels.stream().map(MongoEventModel::getEventData).toList();
    }

    @Override
    public List<AGG_ID> getAggregateIds() {
        List<MongoEventModel> mongoEventModels = baseEventStoreRepository.findAll();
        if (mongoEventModels.isEmpty())
            throw new RuntimeException("No events exists");
        return (List<AGG_ID>) mongoEventModels.stream().map(BaseEventModel::getAggregateId).distinct().toList();
    }
}
