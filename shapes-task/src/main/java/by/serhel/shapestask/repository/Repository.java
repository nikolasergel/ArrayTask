package by.serhel.shapestask.repository;

import by.serhel.shapestask.entity.Cone;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public interface Repository<T> {
    T getById(int id);
    List<T> getAll();
    void add(T object);
    void addAll(Collection<? extends T> collection);
    void remove(T object);
    void removeAll(Collection<? extends T> collection);
    List<T> findAll(Specification<T> specification);
    List<T> sort(Comparator<? super Cone> comparator);
}
