package by.serhel.shapestask.validator;

import by.serhel.shapestask.entity.Cone;
import by.serhel.shapestask.entity.Point;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class ConeValidatorTest {

    @Test
    public void testIsValidCone_ValidCone_True() {
        Point peak = new Point(1, 1, 1);
        Point base = new Point(1, 5, 1);
        Cone cone = new Cone(1, peak, base, 5);
        boolean actual = ConeValidator.isValidCone(cone);
        assertTrue(actual);
    }

    @Test
    public void testIsValidCone_EqualPoints_False() {
        Point base = new Point(1, 1, 1);
        Cone cone = new Cone(1, base, base, 5);
        boolean actual = ConeValidator.isValidCone(cone);
        assertFalse(actual);
    }

    @Test
    public void testIsValidCone_InvalidRadius_False() {
        Point peak = new Point(5, 1, 1);
        Point base = new Point(1, 1, 1);
        Cone cone = new Cone(1, peak, base, -5);
        boolean actual = ConeValidator.isValidCone(cone);
        assertFalse(actual);
    }
}