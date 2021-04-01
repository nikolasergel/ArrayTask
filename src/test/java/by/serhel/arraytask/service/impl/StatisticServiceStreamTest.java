package by.serhel.arraytask.service.impl;

import by.serhel.arraytask.entity.Array;
import by.serhel.arraytask.exception.ArrayException;
import by.serhel.arraytask.service.StatisticService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class StatisticServiceStreamTest {
    private Array array;
    private StatisticService statisticService;

    @BeforeClass
    public void setUp() throws ArrayException {
        this.array = new Array(new int[]{-1, 10, 8, 15, -3, -18});
        this.statisticService = new StatisticServiceStream();
    }

    @Test
    public void testAvg() throws ArrayException {
        int expected = 1;
        int actual = statisticService.avg(array);
        assertEquals(actual, expected);
    }

    @Test(expectedExceptions = ArrayException.class)
    public void testAvgException() throws ArrayException {
        statisticService.avg(null);
    }

    @Test
    public void testSum() throws ArrayException {
        int expected = 11;
        int actual = statisticService.sum(array);
        assertEquals(actual, expected);
    }

    @Test(expectedExceptions = ArrayException.class)
    public void testSumException() throws ArrayException {
        statisticService.sum(null);
    }

    @Test
    public void testCountPositiveNumbers() throws ArrayException {
        int expected = 3;
        int actual = statisticService.countPositiveNumbers(array);
        assertEquals(actual, expected);
    }

    @Test(expectedExceptions = ArrayException.class)
    public void testCountPositiveNumbersException() throws ArrayException {
        statisticService.countPositiveNumbers(null);
    }

    @Test
    public void testCountNegativeNumbers() throws ArrayException {
        int expected = 3;
        int actual = statisticService.countNegativeNumbers(array);
        assertEquals(actual, expected);
    }

    @Test(expectedExceptions = ArrayException.class)
    public void testCountNegativeNumbersException() throws ArrayException {
        statisticService.countNegativeNumbers(null);
    }
}