package by.serhel.xmlparsing.entity;

import java.util.Objects;

public class Ingredient {
    private String name;
    private int weightMG;

    public Ingredient() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeightMG(int weightMG) {
        this.weightMG = weightMG;
    }

    public String getName() {
        return name;
    }

    public int getWeightMG() {
        return weightMG;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return weightMG == that.weightMG && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, weightMG);
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("\n\tingredient {");
        builder.append("\n\t\tname: ").append(name);
        builder.append("\n\t\tweightMG: ").append(weightMG).append("\n\t}\n\t");
        return builder.toString();
    }
}
