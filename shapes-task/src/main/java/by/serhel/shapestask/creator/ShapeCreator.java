package by.serhel.shapestask.creator;

import by.serhel.shapestask.entity.Shape;

public abstract class ShapeCreator {
    private int id = 0;

    public abstract Shape createShape(double[] array);

    public int getId() {
        id++;
        return id;
    }
}
