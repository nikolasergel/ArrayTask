package by.serhel.shapestask.entity;

import java.util.Objects;

public abstract class Shape {
    private final int id;

    public Shape(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shape shape = (Shape) o;
        return id == shape.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
