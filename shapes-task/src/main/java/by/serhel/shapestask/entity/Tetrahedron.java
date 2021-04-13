package by.serhel.shapestask.entity;

import java.util.Objects;

public class Tetrahedron extends Shape {
    private final Point peak;
    private final Triangle base;

    public Tetrahedron(int id, Point peak, Triangle base) {
        super(id);
        this.peak = peak;
        this.base = base;
    }

    public Point getPeak() {
        return peak;
    }

    public Triangle getBase() {
        return base;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tetrahedron that = (Tetrahedron) o;
        return peak.equals(that.peak) && base.equals(that.base);
    }

    @Override
    public int hashCode() {
        return Objects.hash(peak, base);
    }
}
