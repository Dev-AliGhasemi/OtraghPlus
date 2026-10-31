package ir.mrmoshkel.framework.repository;

import ir.mrmoshkel.framework.model.DataModel;
import org.jmolecules.ddd.annotation.Repository;

import java.util.List;
import java.util.Optional;

public interface BaseRepository<T extends DataModel<ID> ,ID> {
    Optional<T> findById(ID id);
    List<T> findAll();
    List<T> findAll(Long offset, Integer pageSize);
    void save(T t);
    void save(T t, boolean isNew);
    void delete(T t);
    void deleteById(ID id);
}
