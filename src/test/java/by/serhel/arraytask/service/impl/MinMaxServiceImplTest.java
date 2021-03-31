package by.serhel.arraytask.service.impl;

import by.serhel.arraytask.entity.Array;
import by.serhel.arraytask.exception.ArrayException;
import by.serhel.arraytask.service.MinMaxService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class MinMaxServiceImplTest {
    private Array array;
    private MinMaxService minMaxService;

    @BeforeClass
    public void setArray() throws ArrayException {
        this.array = new Array(new int[]{-1, 10, 8, 15, -9, -18});
    }

    @BeforeClass
    public void setMinMaxService(){
        this.minMaxService = new MinMaxServiceImpl();
    }

    @Test
    public void testMaxValue() throws ArrayException {
        int expected = 15;
        int actual = minMaxService.maxValue(array);
        assertEquals(actual, expected);
    }

    @Test(expectedExceptions = ArrayException.class)
    public void testMaxValueException() throws ArrayException {
        minMaxService.maxValue(null);
    }

    @Test
    public void testMinValue() throws ArrayException {
        int expected = -18;
        int actual = minMaxService.minValue(array);
        assertEquals(actual, expected);
    }

    @Test(expectedExceptions = ArrayException.class)
    public void testMinValueException() throws ArrayException {
        minMaxService.minValue(null);
    }
}