package ir.mrmoshkel.eventstore;

import org.springframework.stereotype.Repository;

import java.util.UUID;

//Locate this repo in correct place of clean arch layers.
@Repository
public interface HomeEventStoreRepository extends BaseEventStoreRepository<UUID> {
}
