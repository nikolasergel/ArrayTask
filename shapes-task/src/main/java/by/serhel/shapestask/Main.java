package by.serhel.shapestask;

import by.serhel.shapestask.entity.Cone;
import by.serhel.shapestask.entity.Point;
import by.serhel.shapestask.exception.ShapeException;
import by.serhel.shapestask.service.ConeService;
import by.serhel.shapestask.service.impl.ConeServiceImpl;

public class Main {
    public static void main(String[] args) {
        Point base = new Point(1, 1, 1);
        Point peak = new Point(5, 1, 1);
        Cone cone = new Cone(1, base, peak, 5);
        ConeService coneService = new ConeServiceImpl();
        try{
            System.out.println("surface area: " + coneService.calculateArea(cone));
            System.out.println("volume: " + coneService.calculateVolume(cone));
            System.out.println("test: " + coneService.calculateArea(null));
        }
        catch (ShapeException e){
            e.printStackTrace();
        }
    }
}
