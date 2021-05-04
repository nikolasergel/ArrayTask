package by.serhel.shapestask.service.impl;

import by.serhel.shapestask.entity.Point;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class PointServiceImplTest {

    @DataProvider(name = "point-data")
    public Object[][] data(){
        return new Object[][]{
                { 5, new Point(1, 1, 1), new Point(4, 1, 5) },
                { 13, new Point(1, 1, 1), new Point(4, 5, 13) },
                { 97, new Point(66, 73, 1), new Point(1, 1, 1) }
        };
    }

    @Test(dataProvider = "point-data")
    public void testCalculateLength(double expected, Point first, Point second) {
        PointServiceImpl service = new PointServiceImpl();
        double actual = service.calculateLength(first, second);
        assertEquals(actual, expected);
    }
}