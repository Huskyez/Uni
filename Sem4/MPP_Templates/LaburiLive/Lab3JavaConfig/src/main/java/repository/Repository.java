package repository;

public interface Repository<ID, E> {
    void save(E entity);
    void delete(ID id);
    int size();
    E findOne(ID id);
    Iterable<E> findAll();
}
