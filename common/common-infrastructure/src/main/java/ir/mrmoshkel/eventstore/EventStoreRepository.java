package ir.mrmoshkel.eventstore;

import ir.mrmoshkel.model.MongoEventModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventStoreRepository extends MongoRepository<MongoEventModel, Long> {
    <ID> List<MongoEventModel<ID>> findByAggregateId(ID aggregateId);
}
