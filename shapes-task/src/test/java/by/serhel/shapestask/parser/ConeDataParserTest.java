package by.serhel.shapestask.parser;

import by.serhel.shapestask.exception.ShapeException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ConeDataParserTest {

    @DataProvider(name = "validData")
    public Object[][] validData(){
        return new Object[][]{
                {"1 1 1 4 4 4 3", new double[]{ 1, 1, 1, 4, 4, 4, 3 }},
                {"1 -2 3 4 0 -4 3", new double[]{ 1, -2, 3, 4, 0, -4, 3 }},
                {"-1.0 -2 3.2 4 0.0 -3.5 3", new double[]{ -1, -2, 3.2, 4, 0, -3.5, 3 }},
        };
    }

    @DataProvider(name = "invalidData")
    public Object[] invalidData(){
        return new Object[]{
                "1 1 1 4 4 4 -3",
                "1 -2 3 4   0 -4 3",
                "-1.0 5 -2 3.2 4 0.0 -3.5 3",
                "fgukfbek yfg !"
        };
    }

    @Test(dataProvider = "validData")
    public void testParseConeString_ValidData_ReturnDoubleArray(String coneData, double[] expected) throws ShapeException {
        ConeDataParser parser = new ConeDataParser();
        double[] actual = parser.parseConeString(coneData);
        assertEquals(expected, actual);
    }

    @Test(dataProvider = "invalidData", expectedExceptions = ShapeException.class)
    public void testParseConeString_InValidData_ThrowException(String coneData) throws ShapeException {
        ConeDataParser parser = new ConeDataParser();
        double[] actual = parser.parseConeString(coneData);
    }
}