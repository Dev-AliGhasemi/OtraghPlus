package ir.mrmoshkel.common.infrastructure.eventstore;

import ir.mrmoshkel.common.infrastructure.model.MongoEventModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaseEventStoreRepository<AGG_ID> extends MongoRepository<MongoEventModel<AGG_ID>, String> {
    List<MongoEventModel<AGG_ID>> findByAggregateId(AGG_ID aggregateId);
}
