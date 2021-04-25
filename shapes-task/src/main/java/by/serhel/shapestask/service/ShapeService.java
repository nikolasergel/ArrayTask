package by.serhel.shapestask.service;

import by.serhel.shapestask.entity.Shape;
import by.serhel.shapestask.exception.ShapeException;

public interface ShapeService {
    double calculateArea(Shape shape) throws ShapeException;
    double calculateVolume(Shape shape) throws ShapeException;
    boolean intersectAxisX(Shape shape, int distance) throws ShapeException;
    boolean intersectAxisY(Shape shape, int distance) throws ShapeException;
    boolean intersectAxisZ(Shape shape, int distance) throws ShapeException;
}
