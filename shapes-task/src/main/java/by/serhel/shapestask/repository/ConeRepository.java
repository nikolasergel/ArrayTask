package by.serhel.shapestask.repository;

import by.serhel.shapestask.entity.Cone;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public interface ConeRepository {
    Cone getById(int id);
    List<Cone> getAll();
    void add(Cone object);
    void addAll(Collection<? extends Cone> collection);
    void remove(Cone object);
    void removeAll(Collection<? extends Cone> collection);
    List<Cone> findAll(Specification<Cone> specification);
    List<Cone> sort(Comparator<? super Cone> comparator);
}
