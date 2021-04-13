package by.serhel.shapestask.service.impl;

import by.serhel.shapestask.entity.Point;
import by.serhel.shapestask.service.PointService;

public class PointServiceImpl implements PointService {
    @Override
    public double calculateLength(Point A, Point B) {
        double xLength = Math.abs(A.getX() - B.getX());
        double yLength = Math.abs(A.getY() - B.getY());
        double zLength = Math.abs(A.getZ() - B.getZ());
        double length = Math.sqrt(Math.pow(xLength, 2) + Math.pow(yLength, 2) + Math.pow(zLength, 2));
        return length;
    }
}
