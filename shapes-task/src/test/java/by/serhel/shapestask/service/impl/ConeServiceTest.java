package by.serhel.shapestask.service.impl;

import by.serhel.shapestask.entity.Cone;
import by.serhel.shapestask.entity.Point;
import by.serhel.shapestask.exception.ShapeException;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ConeServiceTest {
    private ConeService service = new ConeService();

    @Test
    public void testCalculateArea() throws ShapeException {
        Point peak = new Point(4, 1, 1);
        Point base = new Point(1, 1, 1);
        double radius = 4;
        Cone cone = new Cone(1, peak, base, radius);
        long expected = 113;
        long actual = Math.round(service.calculateArea(cone));
        assertEquals(actual, expected);
    }

    @Test(expectedExceptions = ShapeException.class)
    public void testCalculateArea_NullCone_ThrownShapeException() throws ShapeException {
        service.calculateArea(null);
    }

    @Test
    public void testCalculateVolume() throws ShapeException {
        Point peak = new Point(4, 1, 1);
        Point base = new Point(1, 1, 1);
        double radius = 4;
        Cone cone = new Cone(1, peak, base, radius);
        long expected = 50;
        long actual = Math.round(service.calculateVolume(cone));
        assertEquals(actual, expected);
    }

    @Test(expectedExceptions = ShapeException.class)
    public void testCalculateVolume_NullCone_ThrownShapeException() throws ShapeException {
        service.calculateVolume(null);
    }

    @Test
    public void testIntersectAxisX_DistanceEqualOne_True() throws ShapeException {
        Point peak = new Point(4, 1, 1);
        Point base = new Point(-1, 1, 1);
        double radius = 4;
        Cone cone = new Cone(1, peak, base, radius);
        boolean actual = service.intersectAxisX(cone, 1);
        assertTrue(actual);
    }

    @Test
    public void testIntersectAxisX_DistanceEqualTen_False() throws ShapeException {
        Point peak = new Point(4, 1, 1);
        Point base = new Point(-1, 1, 1);
        double radius = 4;
        Cone cone = new Cone(1, peak, base, radius);
        boolean actual = service.intersectAxisX(cone, 10);
        assertFalse(actual);
    }



    @Test(expectedExceptions = ShapeException.class)
    public void testIntersectAxisX_NullCone_ThrownShapeException() throws ShapeException {
        service.intersectAxisX(null, 1);
    }

    @Test
    public void testIntersectAxisY_DistanceEqualOne_True() throws ShapeException {
        Point peak = new Point(1, 4, 1);
        Point base = new Point(1, -1, 1);
        double radius = 4;
        Cone cone = new Cone(1, peak, base, radius);
        boolean actual = service.intersectAxisY(cone, 1);
        assertTrue(actual);
    }

    @Test
    public void testIntersectAxisY_DistanceEqualTen_False() throws ShapeException {
        Point peak = new Point(1, 4, 1);
        Point base = new Point(1, 1, 1);
        double radius = 4;
        Cone cone = new Cone(1, peak, base, radius);
        boolean actual = service.intersectAxisY(cone, 10);
        assertFalse(actual);
    }

    @Test(expectedExceptions = ShapeException.class)
    public void testIntersectAxisY_NullCone_ThrownShapeException() throws ShapeException {
        service.intersectAxisY(null, 1);
    }

    @Test
    public void testIntersectAxisZ_DistanceEqualOne_True() throws ShapeException {
        Point peak = new Point(1, 1, 4);
        Point base = new Point(1, 1, -1);
        double radius = 4;
        Cone cone = new Cone(1, peak, base, radius);
        boolean actual = service.intersectAxisZ(cone, 1);
        assertTrue(actual);
    }

    @Test
    public void testIntersectAxisZ_DistanceEqualTen_False() throws ShapeException {
        Point peak = new Point(1, 1, 4);
        Point base = new Point(1, 1, -1);
        double radius = 4;
        Cone cone = new Cone(1, peak, base, radius);
        boolean actual = service.intersectAxisZ(cone, 10);
        assertFalse(actual);
    }

    @Test(expectedExceptions = ShapeException.class)
    public void testIntersectAxisZ_NullCone_ThrownShapeException() throws ShapeException {
        service.intersectAxisZ(null, 1);
    }
}