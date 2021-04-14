package by.serhel.shapestask.service.impl;

import by.serhel.shapestask.entity.Cone;
import by.serhel.shapestask.exception.ShapeException;
import by.serhel.shapestask.service.ConeService;
import by.serhel.shapestask.service.PointService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConeServiceImpl implements ConeService {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public double calculateArea(Cone cone) throws ShapeException {
        if(cone == null){
            logger.error("calculateArea method: cone equals null!");
            throw new ShapeException("Cone can't be null!");
        }
        PointService service = new PointServiceImpl();
        double radius = cone.getRadius();
        double height = service.calculateLength(cone.getPeak(), cone.getBase());
        double formingLine = Math.sqrt(Math.pow(radius, 2) + Math.pow(height, 2));
        double area = Math.PI * Math.pow(radius, 2) + Math.PI * radius * formingLine;
        return area;
    }

    @Override
    public double calculateVolume(Cone cone) throws ShapeException {
        if(cone == null){
            logger.error("calculateVolume method: cone equals null!");
            throw new ShapeException("Cone can't be null!");
        }
        PointService service = new PointServiceImpl();
        double height = service.calculateLength(cone.getPeak(), cone.getBase());
        double volume = (Math.PI * Math.pow(cone.getRadius(), 2) * height) / 3;
        return volume;
    }
}