package by.serhel.shapestask.repository;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public interface ConeRepository<T> {
    T getById(int id);
    List<T> getAll();
    void add(T object);
    void addAll(Collection<? extends T> collection);
    void remove(T object);
    void removeAll(Collection<? extends T> collection);
    List<T> query(Specification<T> specification);
    List<T> sort(Comparator<? super T> comparator);
}
