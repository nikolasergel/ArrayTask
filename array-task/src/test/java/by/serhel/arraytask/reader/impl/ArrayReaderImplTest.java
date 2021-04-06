package by.serhel.arraytask.reader.impl;

import by.serhel.arraytask.exception.ResourceFileException;
import by.serhel.arraytask.reader.ArrayReader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Stream;

public class ArrayReaderImplTest {
    private ArrayReader arrayReader;

    @BeforeClass
    public void setUp(){
        this.arrayReader = new ArrayReaderImpl();
    }

    @Test
    public void testReadToStreamValidFile() throws ResourceFileException {
        String[] lines = {"1, 2, -5, 8, 1000", "-10, 3, 9, -109", "5, 6"};
        Stream<String> expected = Arrays.stream(lines);
        Stream<String> actual = arrayReader.readToStream("data/array.txt");
    }

    @Test
    public void testReadToStreamIncorrectFile() throws ResourceFileException {
        String[] lines = { "1, 2, 3", "9, 50, 0" };
        Stream<String> expected = Arrays.stream(lines);
        Stream<String> actual = arrayReader.readToStream("data/bad-array.txt");
    }

    @Test(expectedExceptions = ResourceFileException.class)
    public void testReadToStreamException() throws ResourceFileException {
        arrayReader.readToStream("data/not-exists.txt");
    }
}