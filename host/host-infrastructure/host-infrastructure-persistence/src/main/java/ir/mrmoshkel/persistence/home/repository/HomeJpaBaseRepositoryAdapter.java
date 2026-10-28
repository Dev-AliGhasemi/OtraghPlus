package ir.mrmoshkel.persistence.home.repository;

import ir.mrmoshkel.home.enumeration.ReserveState;
import ir.mrmoshkel.home.model.HomeModel;
import ir.mrmoshkel.home.repository.HomeBaseRepository;
import ir.mrmoshkel.persistence.framework.JpaRepositoryAdapter;
import ir.mrmoshkel.persistence.home.entity.HomeEntity;
import ir.mrmoshkel.persistence.home.mapper.HomeJpaMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Spliterators;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
@org.jmolecules.ddd.annotation.Repository
public class HomeJpaBaseRepositoryAdapter extends JpaRepositoryAdapter<HomeEntity, UUID, HomeJpaMapper> implements HomeBaseRepository {

    public HomeJpaBaseRepositoryAdapter(HomeJpaRepository homeJpaRepository) {
        super(homeJpaRepository, HomeJpaMapper.INSTANCE);
    }

    @Override
    public Optional<HomeModel> findById(UUID uuid) {
        return Optional.ofNullable(getRepository().findById(uuid).map(getMapper()::toModel).orElseThrow(() -> new RuntimeException("Not found")));
    }

    @Override
    public List<HomeModel> findAll() {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(getRepository().findAll().iterator(), 0), false)
                .map(getMapper()::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<HomeModel> findAll(Long offset, Integer pageSize) {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(((HomeJpaRepository) getRepository())
                        .findAll(PageRequest.of((int) (offset/pageSize), pageSize)).iterator(), 0), false)
                .map(getMapper()::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public void save(HomeModel homeModel, boolean isNew) {
        HomeEntity homeEntity = getMapper().toEntity(homeModel);
        homeEntity.setNew(isNew);
        getRepository().save(homeEntity);
    }

    @Override
    public void save(HomeModel homeModel) {
        save(homeModel, true);
    }

    @Override
    public void delete(HomeModel homeModel) {
        getRepository().delete(getMapper().toEntity(homeModel));
    }

    @Override
    public void deleteById(UUID uuid) {
        getRepository().deleteById(uuid);
    }

    @Override
    public void reserve(HomeModel homeModel) {
        ((HomeJpaRepository) getRepository()).reserve(homeModel.getId(), ReserveState.RESERVED);
    }
}
