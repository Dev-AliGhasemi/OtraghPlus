package ir.mrmoshkel.home.repository;

import ir.mrmoshkel.framework.repository.BaseRepository;
import ir.mrmoshkel.home.model.HomeModel;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HomeBaseRepository extends BaseRepository<HomeModel, UUID> {
    void reserve(HomeModel homeModel);
}
