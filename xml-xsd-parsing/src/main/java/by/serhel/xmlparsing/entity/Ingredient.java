package by.serhel.xmlparsing.entity;

import java.util.Objects;

public class Ingredient {
    private String name;
    private int weightMG;

    public Ingredient(String name, int weightMG) {
        this.name = name;
        this.weightMG = weightMG;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeightMG(int weightMG) {
        this.weightMG = weightMG;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return weightMG == that.weightMG && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, weightMG);
    }
}
