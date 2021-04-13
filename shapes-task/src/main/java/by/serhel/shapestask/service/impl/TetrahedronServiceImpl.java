package by.serhel.shapestask.service.impl;

import by.serhel.shapestask.entity.Point;
import by.serhel.shapestask.entity.Tetrahedron;
import by.serhel.shapestask.entity.Triangle;
import by.serhel.shapestask.exception.ShapeException;
import by.serhel.shapestask.service.TetrahedronService;
import by.serhel.shapestask.service.TriangleService;

public class TetrahedronServiceImpl implements TetrahedronService {
    @Override
    public double calculateArea(Tetrahedron tetrahedron) throws ShapeException {
        TriangleService service = new TriangleServiceImpl();
        Point pointS = tetrahedron.getPeak();
        Triangle base = tetrahedron.getBase();
        double areaABC = service.calculateArea(base);
        double areaSAB = service.calculateArea(pointS, base.getA(), base.getB());
        double areaSBC = service.calculateArea(pointS, base.getB(), base.getC());
        double areaSCA = service.calculateArea(pointS, base.getC(), base.getA());
        double area = areaABC + areaSBC +areaSAB + areaSAB;
        return area;
    }

    @Override
    public double calculateVolume(Tetrahedron tetrahedron) throws ShapeException {
        return 0;
    }
}
