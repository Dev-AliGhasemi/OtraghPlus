package ir.mrmoshkel.host.infrastructure.persistence.framework;

import lombok.Getter;
import org.springframework.data.jpa.repository.JpaRepository;

@Getter
public abstract class JpaRepositoryAdapter<T,ID, M> {
    private final JpaRepository<T,ID> repository;
    private final M mapper;

    public JpaRepositoryAdapter(JpaRepository<T, ID> repository, M mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
}
