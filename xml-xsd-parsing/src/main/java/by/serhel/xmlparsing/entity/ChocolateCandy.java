package by.serhel.xmlparsing.entity;

import by.serhel.xmlparsing.entity.type.ChocolateType;

import java.util.Objects;

public class ChocolateCandy extends Candy{
    private ChocolateType chocolateType;

    public ChocolateCandy() {
        super();
    }

    public ChocolateCandy(ChocolateType chocolateType) {
        super();
        this.chocolateType = chocolateType;
    }

    public ChocolateType getChocolateType() {
        return chocolateType;
    }

    public void setChocolateType(ChocolateType chocolateType) {
        this.chocolateType = chocolateType;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ChocolateCandy that = (ChocolateCandy) o;
        return chocolateType == that.chocolateType;
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), chocolateType);
    }
}
