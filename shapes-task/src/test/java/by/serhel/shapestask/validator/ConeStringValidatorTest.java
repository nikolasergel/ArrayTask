package by.serhel.shapestask.validator;

import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class ConeStringValidatorTest {

    @Test
    public void testIsValid_NegativeCoordinates_True() {
        String invalidData = "1 2 -3 4 -4 5 7";
        assertTrue(ConeStringValidator.isValid(invalidData));
    }

    @Test
    public void testIsValid_DoubleNumbers_True() {
        String invalidData = "1.1 2 3 4 -4.0 5 7.5";
        assertTrue(ConeStringValidator.isValid(invalidData));
    }

    @Test
    public void testIsValid_NegativeRadius_False() {
        String invalidData = "0 0 0 1 1 1 -7";
        assertFalse(ConeStringValidator.isValid(invalidData));
    }

    @Test
    public void testIsValid_NotEnoughNumbers_False() {
        String invalidData = "0 0 0 -1 1 7";
        assertFalse(ConeStringValidator.isValid(invalidData));
    }

    @Test
    public void testIsValid_BadNumber_False() {
        String invalidData = "0 0. 0 -1 1 1 -7";
        assertFalse(ConeStringValidator.isValid(invalidData));
    }

    @Test
    public void testIsValid_LotsOfWhiteSpaces_False() {
        String invalidData = "0 0 0 1 1 1 -7   ";
        assertFalse(ConeStringValidator.isValid(invalidData));
    }

    @Test
    public void testIsValid_RandomString_False() {
        String invalidData = "grehgeog gjege jtb',c";
        assertFalse(ConeStringValidator.isValid(invalidData));
    }
}