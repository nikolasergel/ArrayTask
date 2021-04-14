package by.serhel.shapestask.service;

import by.serhel.shapestask.entity.Cone;
import by.serhel.shapestask.exception.ShapeException;

public interface ConeService {
    double calculateArea(Cone cone) throws ShapeException;
    double calculateVolume(Cone cone) throws ShapeException;
}
