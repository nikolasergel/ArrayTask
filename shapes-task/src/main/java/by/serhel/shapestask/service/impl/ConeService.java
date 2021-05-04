package by.serhel.shapestask.service.impl;

import by.serhel.shapestask.entity.Cone;
import by.serhel.shapestask.entity.Point;
import by.serhel.shapestask.entity.Shape;
import by.serhel.shapestask.exception.ShapeException;
import by.serhel.shapestask.service.PointService;
import by.serhel.shapestask.service.ShapeService;

public class ConeService implements ShapeService {

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

    @Override
    public boolean intersectAxisX(Shape shape, int distance) throws ShapeException {
        if(shape == null){
            throw new ShapeException("Shape can't be null!");
        }

        boolean isIntersecting = false;
        if(shape instanceof Cone cone){
            Point base = cone.getBase();
            Point peak = cone.getPeak();
            if(base.getX() * peak.getX() < 0 && base.getY() * peak.getY() > 0 && base.getZ() * peak.getZ() > 0){
                isIntersecting = distance <= Math.abs(base.getX()) || distance <= Math.abs(peak.getX());
            }
        }
        else{
            throw new ShapeException("Can't cast '" + shape.getClass() + "' to Cone.class!");
        }
        return isIntersecting;
    }

    @Override
    public boolean intersectAxisY(Shape shape, int distance) throws ShapeException {
        if(shape == null){
            throw new ShapeException("Shape can't be null!");
        }

        boolean isIntersecting = false;
        if(shape instanceof Cone cone){
            Point base = cone.getBase();
            Point peak = cone.getPeak();
            if(base.getY() * peak.getY() < 0 && base.getX() * peak.getX() > 0 && base.getZ() * peak.getZ() > 0){
                isIntersecting = distance <= Math.abs(base.getY()) || distance <= Math.abs(peak.getY());
            }
        }
        else{
            throw new ShapeException("Can't cast '" + shape.getClass() + "' to Cone.class!");
        }
        return isIntersecting;
    }

    @Override
    public boolean intersectAxisZ(Shape shape, int distance) throws ShapeException {
        if(shape == null){
            throw new ShapeException("Shape can't be null!");
        }

        boolean isIntersecting = false;
        if(shape instanceof Cone cone){
            Point base = cone.getBase();
            Point peak = cone.getPeak();
            if(base.getZ() * peak.getZ() < 0 && base.getY() * peak.getY() > 0 && base.getX() * peak.getX() > 0){
                isIntersecting = distance <= Math.abs(base.getZ()) || distance <= Math.abs(peak.getZ());
            }
        }
        else{
            throw new ShapeException("Can't cast '" + shape.getClass() + "' to Cone.class!");
        }
        return isIntersecting;
    }
}