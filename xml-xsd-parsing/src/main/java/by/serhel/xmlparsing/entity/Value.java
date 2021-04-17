package by.serhel.xmlparsing.entity;

import java.util.Objects;

public class Value {
    private double fats;
    private double carbohydrates;
    private double proteins;

    public Value() {
    }

    public Value(double fats, double carbohydrates, double proteins) {
        this.fats = fats;
        this.carbohydrates = carbohydrates;
        this.proteins = proteins;
    }

    public double getFats() {
        return fats;
    }

    public void setFats(double fats) {
        this.fats = fats;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public double getProteins() {
        return proteins;
    }

    public void setProteins(double proteins) {
        this.proteins = proteins;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Value value = (Value) o;
        return value.fats == fats
                && value.carbohydrates == carbohydrates
                && value.proteins == proteins;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fats, carbohydrates, proteins);
    }
}
