package by.serhel.shapestask.entity;

import by.serhel.shapestask.observer.Observable;
import by.serhel.shapestask.observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class Cone extends Shape implements Observable {
    private Point peak;
    private Point base;
    private double radius;
    private List<Observer> observers;

    public Cone(int id, Point peak, Point base, double radius) {
        super(id);
        this.peak = peak;
        this.base = base;
        this.radius = radius;
        this.observers = new ArrayList<>();
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

    public void setPeak(Point peak) {
        this.peak = peak;
        notifyObservers();
    }

    public void setBase(Point base) {
        this.base = base;
        notifyObservers();
    }

    public void setRadius(double radius) {
        this.radius = radius;
        notifyObservers();
    }

    @Override
    public void notifyObservers() {
        if(observers.size() > 0){
            for(Observer observer : observers){
                observer.update(this);
            }
        }
    }

    @Override
    public void addObserver(Observer observer) {
        if(observer != null){
            this.observers.add(observer);
        }
    }

    @Override
    public void removeObserver(Observer observer) {
        if(observer != null){
            this.observers.remove(observer);
        }
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