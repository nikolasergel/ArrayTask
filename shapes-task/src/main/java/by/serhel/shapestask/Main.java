package by.serhel.shapestask;

import by.serhel.shapestask.entity.Cone;
import by.serhel.shapestask.entity.Point;
import by.serhel.shapestask.exception.CustomFileException;
import by.serhel.shapestask.exception.ShapeException;
import by.serhel.shapestask.reader.CustomFileReader;
import by.serhel.shapestask.service.ShapeService;
import by.serhel.shapestask.service.impl.ConeServiceImpl;

public class Main {
    public static void main(String[] args) {
        Point base = new Point(1, 1, 1);
        Point peak = new Point(5, 1, 1);
        Cone cone = new Cone(1, base, peak, 5);
        ShapeService coneService = new ConeServiceImpl();
        try{
            CustomFileReader reader = new CustomFileReader();
            reader.readLines("data/cone-data.txt").forEach(System.out::println);
            System.out.println("surface area: " + coneService.calculateArea(cone));
            System.out.println("volume: " + coneService.calculateVolume(cone));
            System.out.println(cone);
        }
        catch (ShapeException | CustomFileException e){
            e.printStackTrace();
        }
    }
}
