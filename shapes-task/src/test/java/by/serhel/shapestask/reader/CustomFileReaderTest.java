package by.serhel.shapestask.reader;

import by.serhel.shapestask.exception.CustomFileException;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.testng.Assert.assertEquals;

public class CustomFileReaderTest {

    @Test
    public void testReadLines() throws CustomFileException {
        List<String> expected = Stream.of("3.0 3.0 3.0 1.0 1.0 1.0 4.0",
                "4.0 3.0 7.0 1.0 2.0 1.0 3.0", "4.0 3.0 7.0 1.0 2.0 1.0 -3.0")
                .collect(Collectors.toList());
        CustomFileReader reader = new CustomFileReader();
        List<String> actual = reader.readLines("data/cone-data.txt")
                .collect(Collectors.toList());
        assertEquals(actual, expected);
    }

    @Test(expectedExceptions = CustomFileException.class)
    public void testReadLines_BadFilePath_ThrownCustomFileException() throws CustomFileException {
        CustomFileReader reader = new CustomFileReader();
        reader.readLines("data/not-exists.txt");
    }
}