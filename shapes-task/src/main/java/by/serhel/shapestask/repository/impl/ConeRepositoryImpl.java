package by.serhel.shapestask.repository.impl;

import by.serhel.shapestask.entity.Cone;
import by.serhel.shapestask.repository.ConeRepository;
import by.serhel.shapestask.repository.Specification;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ConeRepositoryImpl implements ConeRepository<Cone> {
    private List<Cone> coneList;
    private static ConeRepositoryImpl instance;

    private ConeRepositoryImpl() {
        this.coneList = new ArrayList<>();
    }

    public static ConeRepositoryImpl getInstance() {
        if(instance == null){
            instance = new ConeRepositoryImpl();
        }
        return instance;
    }

    @Override
    public Cone getById(int id) {
        return coneList.stream()
                .filter(cone -> cone.getId() == id)
                .findFirst()
                .get();
    }

    @Override
    public List<Cone> getAll(){
        return List.copyOf(coneList);
    }

    @Override
    public void add(Cone cone) {
        coneList.add(cone);
    }

    @Override
    public void addAll(Collection<? extends Cone> collection) {
        this.coneList.addAll(collection);
    }

    @Override
    public void remove(Cone cone) {
        this.coneList.remove(cone);
    }

    @Override
    public void removeAll(Collection<? extends Cone> collection) {
        this.coneList.removeAll(collection);
    }

    @Override
    public List<Cone> query(Specification<Cone> specification) {
        return coneList.stream()
                .filter(specification::specify)
                .collect(Collectors.toList());
    }

    @Override
    public List<Cone> sort(Comparator<? super Cone> comparator) {
        return coneList.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }
}
