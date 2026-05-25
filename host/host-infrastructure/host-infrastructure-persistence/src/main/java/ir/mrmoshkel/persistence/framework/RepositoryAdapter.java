package ir.mrmoshkel.persistence.framework;

import lombok.Getter;
import org.springframework.data.repository.CrudRepository;

@Getter
public abstract class RepositoryAdapter<T,ID, M> {
    private final CrudRepository<T,ID> repository;
    private final M mapper;

    public RepositoryAdapter(CrudRepository<T, ID> repository, M mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
}
