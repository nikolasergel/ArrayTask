package by.serhel.shapestask.service;

import by.serhel.shapestask.entity.Tetrahedron;
import by.serhel.shapestask.exception.ShapeException;

public interface TetrahedronService {
    double calculateArea(Tetrahedron tetrahedron) throws ShapeException;
    double calculateVolume(Tetrahedron tetrahedron) throws ShapeException;
}
