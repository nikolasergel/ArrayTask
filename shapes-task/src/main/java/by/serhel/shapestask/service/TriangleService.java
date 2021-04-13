package by.serhel.shapestask.service;

import by.serhel.shapestask.entity.Point;
import by.serhel.shapestask.entity.Triangle;
import by.serhel.shapestask.exception.ShapeException;

public interface TriangleService {
    double calculateArea(Triangle triangle) throws ShapeException;
    double calculateArea(Point A, Point B, Point C) throws ShapeException;
}
