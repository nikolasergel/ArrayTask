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
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        Cone cone = (Cone) o;
        return (peak == null && cone.getPeak() == null || peak != null && peak.equals(cone.peak))
                && (base == null && cone.getBase() == null || base != null && base.equals(cone.base))
                && (radius == cone.radius);
    }

    @Override
    public int hashCode() {
        int c = 57;
        int result = c;
        result = c * result + (peak == null ? 0 : peak.hashCode());
        result = c * result + (base == null ? 0 : base.hashCode());
        result = c * result + (int)radius;
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Cone{");
        builder.append("\nid=").append(getId());
        builder.append("\npeak: ").append(peak);
        builder.append("\nbase: ").append(base);
        builder.append("\nradius=").append(radius);
        builder.append("\n}");
        return builder.toString();
    }
}