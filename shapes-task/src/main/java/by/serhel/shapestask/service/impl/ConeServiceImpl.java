package by.serhel.shapestask.service.impl;

import by.serhel.shapestask.entity.Cone;
import by.serhel.shapestask.entity.Shape;
import by.serhel.shapestask.exception.ShapeException;
import by.serhel.shapestask.service.ShapeService;
import by.serhel.shapestask.service.PointService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConeServiceImpl implements ShapeService {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public double calculateArea(Shape shape) throws ShapeException {
        if(shape == null){
            throw new ShapeException("Shape can't be null!");
        }
        double area;
        if(shape instanceof Cone cone){
            PointService service = new PointServiceImpl();
            double radius = cone.getRadius();
            double height = service.calculateLength(cone.getPeak(), cone.getBase());
            double formingLine = Math.sqrt(Math.pow(radius, 2) + Math.pow(height, 2));
            area = Math.PI * Math.pow(radius, 2) + Math.PI * radius * formingLine;
        }
        else{
            throw new ShapeException("Can't cast " + shape.getClass() + " to Cone");
        }
        return area;
    }

    @Override
    public double calculateVolume(Shape shape) throws ShapeException {
        if(shape == null){
            throw new ShapeException("Shape can't be null!");
        }
        double volume;
        if(shape instanceof Cone cone){
            PointService service = new PointServiceImpl();
            double height = service.calculateLength(cone.getPeak(), cone.getBase());
            volume = (Math.PI * Math.pow(cone.getRadius(), 2) * height) / 3;
        }
        else {
            throw new ShapeException("Can't cast '" + shape.getClass() + "' to Cone.class!");
        }
        return volume;
    }
}