package by.serhel.shapestask.creator;

import by.serhel.shapestask.entity.Cone;
import by.serhel.shapestask.entity.Point;
import by.serhel.shapestask.warehouse.Warehouse;

public class ConeCreator extends ShapeCreator{
    @Override
    public Cone createShape(double[] array){
        Point base = new Point(array[0], array[1], array[2]);
        Point peak = new Point(array[3], array[4], array[5]);
        double radius = array[6];
        Cone cone = new Cone(generateId(), peak, base, radius);
        Warehouse.getInstance().add(cone);
        return cone;
    }
}
