package by.serhel.shapestask.service.impl;

import by.serhel.shapestask.entity.Point;
import by.serhel.shapestask.entity.Triangle;
import by.serhel.shapestask.exception.ShapeException;
import by.serhel.shapestask.service.PointService;
import by.serhel.shapestask.service.TriangleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TriangleServiceImpl implements TriangleService {
    private static final Logger logger = LogManager.getLogger();

    public double calculatePerimeter(Triangle triangle) throws ShapeException {
        if(triangle == null){
            logger.error("calculatePerimeter method: Triangle equals null!");
            throw new ShapeException("Triangle can't be null!");
        }
        PointService pointService = new PointServiceImpl();
        double perimeter = pointService.calculateLength(triangle.getA(), triangle.getB())
                + pointService.calculateLength(triangle.getB(), triangle.getC())
                + pointService.calculateLength(triangle.getC(), triangle.getA());
        return perimeter;
    }

    @Override
    public double calculateArea(Triangle triangle) throws ShapeException {
        if(triangle == null){
            logger.error("calculateArea method: Triangle equals null!");
            throw new ShapeException("Triangle can't be null!");
        }

        PointService pointService = new PointServiceImpl();
        double lengthAB = pointService.calculateLength(triangle.getA(), triangle.getB());
        double lengthBC = pointService.calculateLength(triangle.getB(), triangle.getC());
        double lengthCA = pointService.calculateLength(triangle.getC(), triangle.getA());
        double halfPerimeter = lengthAB + lengthBC + lengthCA;
        double area = Math.sqrt(halfPerimeter * (halfPerimeter - lengthAB)
                * (halfPerimeter - lengthBC) * (halfPerimeter - lengthCA));
        return area;
    }

    @Override
    public double calculateArea(Point A, Point B, Point C) throws ShapeException {
        if(A == null || B == null || C == null){
            logger.error("calculateArea method: Point equals null!");
            throw new ShapeException("Point can't be null!");
        }

        PointService pointService = new PointServiceImpl();
        double lengthAB = pointService.calculateLength(A, B);
        double lengthBC = pointService.calculateLength(B, C);
        double lengthCA = pointService.calculateLength(C, A);
        double halfPerimeter = lengthAB + lengthBC + lengthCA;
        double area = Math.sqrt(halfPerimeter * (halfPerimeter - lengthAB)
                * (halfPerimeter - lengthBC) * (halfPerimeter - lengthCA));
        return area;
    }
}
