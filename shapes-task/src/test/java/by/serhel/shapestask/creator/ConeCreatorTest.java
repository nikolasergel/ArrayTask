package by.serhel.shapestask.creator;

import by.serhel.shapestask.entity.Cone;
import by.serhel.shapestask.entity.Point;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ConeCreatorTest {

    @DataProvider(name = "coneData")
    public Object[][] coneData(){
        return new Object[][]{
                {
                    new double[] { 1, 1, 1, 4, 4, 4, 3},
                    1,
                    new Point(1, 1, 1),
                    new Point(4, 4, 4),
                    3
                },
                {
                    new double[] { 1, 2.1, 1, 4, 0, 4, 7},
                    2,
                    new Point(1, 2.1, 1),
                    new Point(4, 0, 4),
                    7
                },
                {
                    new double[] { 0, -10, -8, 10, 0.001, 100, 70.9},
                    3,
                    new Point(0, -10,-8),
                    new Point(10, 0.001, 100),
                    70.9
                }
        };
    }

    @Test(dataProvider = "coneData")
    public void testCreateShape(double[] array, int id, Point base, Point peak, double radius) {
        Cone expected = new Cone(id, base, peak, radius);
        ConeCreator creator = new ConeCreator();
        Cone actual = creator.createShape(array);
        assertEquals(actual, expected);
    }
}