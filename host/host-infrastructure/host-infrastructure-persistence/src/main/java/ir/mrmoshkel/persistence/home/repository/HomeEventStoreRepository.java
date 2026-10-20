package ir.mrmoshkel.persistence.home.repository;

import ir.mrmoshkel.eventstore.BaseEventStoreRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HomeEventStoreRepository extends BaseEventStoreRepository<UUID> {

}
