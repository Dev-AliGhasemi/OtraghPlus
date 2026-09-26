package ir.mrmoshkel.framework.repository;

import ir.mrmoshkel.framework.model.DataModel;

import java.util.List;
import java.util.Optional;

public interface Repository<T extends DataModel<ID> ,ID> {
    Optional<T> findById(ID id);
    List<T> findAll();
    List<T> findAll(Long offset, Integer pageSize);
    void save(T t);
    void delete(T t);
    void deleteById(ID id);
    void reserve(T t);

}
