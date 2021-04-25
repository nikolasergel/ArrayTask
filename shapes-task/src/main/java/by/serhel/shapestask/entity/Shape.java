package by.serhel.shapestask.entity;

public abstract class Shape {
    private final int id;

    public Shape(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
