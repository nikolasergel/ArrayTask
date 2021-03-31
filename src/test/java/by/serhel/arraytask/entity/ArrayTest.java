package by.serhel.arraytask.entity;

import by.serhel.arraytask.exception.ArrayException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ArrayTest {
    private Array array;

    @BeforeMethod
    public void setUp() throws ArrayException {
        this.array = new Array(new int[]{1, 2, 3, 4, 5});
    }

    @Test
    public void testGetLength() {
        int expected = 5;
        int actual = array.getLength();
        assertEquals(actual, expected);
    }

    @Test(expectedExceptions = ArrayException.class)
    public void testGetElementException() throws ArrayException {
        array.getElement(-1);
    }

    @Test
    public void testGetElement() throws ArrayException {
        int expected = 2;
        int actual = array.getElement(1);
        assertEquals(actual, expected);
    }

    @Test
    public void testSetElement() throws ArrayException {
        array.setElement(99, 1);
        int expected = 99;
        int actual = array.getElement(1);
        assertEquals(actual, expected);
    }

    @Test(expectedExceptions = ArrayException.class)
    public void testSetElementException() throws ArrayException {
        array.setElement(-1, -10);
    }
}