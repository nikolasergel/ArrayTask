package by.serhel.shapestask.repository.impl;

import by.serhel.shapestask.entity.Cone;
import by.serhel.shapestask.repository.Specification;

public class IdSpecification implements Specification<Cone> {
    private int id;

    public IdSpecification(int id) {
        this.id = id;
    }

    @Override
    public boolean specify(Cone cone) {
        return cone.getId() == id;
    }
}
