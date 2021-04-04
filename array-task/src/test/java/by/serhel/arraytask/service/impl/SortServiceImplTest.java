package by.serhel.arraytask.service.impl;

import by.serhel.arraytask.entity.Array;
import by.serhel.arraytask.exception.ArrayException;
import by.serhel.arraytask.service.SortService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SortServiceImplTest {
    private Array actualArray;
    private Array expectedArray;
    private SortService sortService;

    @BeforeClass
    public void setUp() throws ArrayException {
        this.expectedArray = new Array(new int[]{-18, -3, -1, 8, 10, 15});
        this.sortService = new SortServiceImpl();
    }

    @BeforeMethod
    public void setArray() throws ArrayException {
        this.actualArray = new Array(new int[]{-1, 10, 8, 15, -3, -18});
    }

    @Test
    public void testBubbleSort() throws ArrayException {
        sortService.bubbleSort(actualArray);
        assertEquals(actualArray, expectedArray);
    }

    @Test(expectedExceptions = ArrayException.class)
    public void testBubbleSortException() throws ArrayException {
        sortService.bubbleSort(null);
    }

    @Test
    public void testQuickSort() throws ArrayException {
        sortService.quickSort(actualArray, 0, actualArray.getLength() - 1);
        assertEquals(actualArray, expectedArray);
    }

    @Test(expectedExceptions = ArrayException.class)
    public void testQuickSortException() throws ArrayException {
        sortService.quickSort(null, 0, actualArray.getLength() - 1);
    }

    @Test
    public void testInsertionSort() throws ArrayException {
        sortService.insertionSort(actualArray);
        assertEquals(actualArray, expectedArray);
    }

    @Test(expectedExceptions = ArrayException.class)
    public void testInsertionSortException() throws ArrayException {
        sortService.insertionSort(null);
    }
}