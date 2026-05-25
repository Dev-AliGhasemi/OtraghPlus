package ir.mrmoshkel.persistence.home.repository;

import ir.mrmoshkel.home.model.HomeModel;
import ir.mrmoshkel.home.repository.HomeRepository;
import ir.mrmoshkel.persistence.framework.RepositoryAdapter;
import ir.mrmoshkel.persistence.home.entity.HomeEntity;
import ir.mrmoshkel.persistence.home.mapper.HomeMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class HomeRepositoryAdapter extends RepositoryAdapter<HomeEntity, Long, HomeMapper> implements HomeRepository {

    public HomeRepositoryAdapter(HomeJpaRepository homeJpaRepository) {
        super(homeJpaRepository, HomeMapper.INSTANCE);
    }

    @Override
    public Optional<HomeModel> findById(Long aLong) {
        return Optional.ofNullable(getRepository().findById(aLong).map(getMapper()::toModel).orElseThrow(() -> new RuntimeException("Not found")));
    }

    @Override
    public List<HomeModel> findAll() {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(getRepository().findAll().iterator(), 0), false)
                .map(getMapper()::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public void save(HomeModel homeModel) {
        getRepository().save(getMapper().toEntity(homeModel));
    }

    @Override
    public void delete(HomeModel homeModel) {
        getRepository().delete(getMapper().toEntity(homeModel));
    }

    @Override
    public void deleteById(Long aLong) {
        getRepository().deleteById(aLong);
    }

    @Override
    public void reserve(HomeModel homeModel) {
        ((HomeJpaRepository) getRepository()).reserve(homeModel.getId(), homeModel.getReserveState());
    }
}
