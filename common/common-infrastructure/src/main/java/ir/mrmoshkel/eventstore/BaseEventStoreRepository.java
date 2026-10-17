package ir.mrmoshkel.eventstore;

import ir.mrmoshkel.model.MongoEventModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface BaseEventStoreRepository<ID> extends MongoRepository<MongoEventModel<ID>, String> {
    <ID> List<MongoEventModel<ID>> findByAggregateId(ID aggregateId);
}
