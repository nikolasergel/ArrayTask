package by.serhel.arraytask.creator;

import by.serhel.arraytask.entity.Array;
import by.serhel.arraytask.exception.ArrayException;
import by.serhel.arraytask.exception.ResourceFileException;
import by.serhel.arraytask.parser.ArrayParser;
import by.serhel.arraytask.reader.ArrayReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Stream;

public class ArrayCreator {
    private static final Logger logger = LogManager.getLogger();
    private final ArrayReader reader;
    private final ArrayParser parser;

    public ArrayCreator(ArrayReader reader, ArrayParser parser){
        this.reader = reader;
        this.parser = parser;
    }
    
    public Array createArray(String filePath) throws ArrayException {
        Array array;
        try {
            Stream<String> lines = reader.readToStream(filePath);
            int[] numbers = parser.parse(lines);
            array = new Array(numbers);
        }
        catch (ResourceFileException resourceFileException) {
            String message = "Invalid array. Array should be not null and longer then 0!";
            logger.error(message);
            throw new ArrayException(message);
        }
        return array;
    }
}
