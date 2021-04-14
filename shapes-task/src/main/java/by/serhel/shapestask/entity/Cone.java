package by.serhel.shapestask.entity;

import java.util.Objects;

public class Cone extends Shape{
    private Point peak;
    private Point base;
    private double radius;

    public Cone(int id, Point peak, Point base, double radius) {
        super(id);
        this.peak = peak;
        this.base = base;
        this.radius = radius;
    }

    public Point getPeak() {
        return peak;
    }

    public Point getBase() {
        return base;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cone cone = (Cone) o;
        return peak.equals(cone.peak) && base.equals(cone.base) && radius == cone.radius;
    }

    @Override
    public int hashCode() {
        return Objects.hash(peak, base, radius);
    }
}