package ir.mrmoshkel.contract;

public interface EventSource<T,ID> {
    T getById(ID id);
    void save(T t);
    void republishEvents();
}
