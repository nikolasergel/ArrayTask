package by.serhel.shapestask.entity;

import java.util.Objects;

public class ConeParameters {
    private int id;
    private double area;
    private double volume;

    public ConeParameters(int id) {
        this.id = id;
    }

    public ConeParameters(int id, double area, double volume) {
        this.id = id;
        this.area = area;
        this.volume = volume;
    }

    public int getId() {
        return id;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConeParameters param = (ConeParameters) o;
        return id == param.id && param.area == area && param.volume == volume;
    }

    @Override
    public int hashCode() {
        int c = 29;
        int result = c;
        result = c * result + id;
        result = c * result + (int)area;
        result = c * result + (int)volume;
        return result;
    }
}
