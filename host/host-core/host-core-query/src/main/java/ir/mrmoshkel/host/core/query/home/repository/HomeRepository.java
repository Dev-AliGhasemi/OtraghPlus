package ir.mrmoshkel.host.core.query.home.repository;

import ir.mrmoshkel.framework.repository.BaseRepository;
import ir.mrmoshkel.host.core.query.home.model.HomeModel;

import java.util.UUID;

public interface HomeRepository extends BaseRepository<HomeModel, UUID> {
    void reserve(HomeModel homeModel);
}
