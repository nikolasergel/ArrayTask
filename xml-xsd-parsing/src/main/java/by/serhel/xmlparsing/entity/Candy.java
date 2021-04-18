package by.serhel.xmlparsing.entity;

import by.serhel.xmlparsing.entity.type.CandyType;
import by.serhel.xmlparsing.entity.type.Origin;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Candy {
    private String name;
    private int energy;
    private CandyType type;
    private List<Ingredient> ingredients;
    private Value value;
    private String production;
    private LocalDate productionDate;
    private Origin origin;

    public Candy() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public CandyType getType() {
        return type;
    }

    public void setType(CandyType type) {
        this.type = type;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public String getProduction() {
        return production;
    }

    public void setProduction(String production) {
        this.production = production;
    }

    public LocalDate getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(LocalDate productionDate) {
        this.productionDate = productionDate;
    }

    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Candy candy = (Candy) o;
        return energy == candy.energy && Objects.equals(name, candy.name) && type == candy.type && Objects.equals(ingredients, candy.ingredients) && Objects.equals(value, candy.value) && Objects.equals(production, candy.production) && Objects.equals(productionDate, candy.productionDate) && origin == candy.origin;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, energy, type, ingredients, value, production, productionDate, origin);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Candy {");
        builder.append("\n\tname: ").append(name);
        builder.append("\n\tenergy: ").append(energy);
        builder.append("\n\ttype: ").append(type);
        builder.append("\n\tingredients: ").append(ingredients);
        builder.append("\n\t").append(value);
        builder.append("\n\tproduction: ").append(production);
        builder.append("\n\tproductionDate: ").append(productionDate);
        builder.append("\n\torigin: ").append(origin).append("\n}");
        return builder.toString();
    }
}
