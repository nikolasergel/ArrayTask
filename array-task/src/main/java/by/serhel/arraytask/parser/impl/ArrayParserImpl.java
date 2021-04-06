package by.serhel.arraytask.parser.impl;

import by.serhel.arraytask.exception.ArrayException;
import by.serhel.arraytask.parser.ArrayParser;
import by.serhel.arraytask.validator.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.stream.Stream;

public class ArrayParserImpl implements ArrayParser {
    private static final Logger logger = LogManager.getLogger();
    private final Validator validator = new Validator();

    public int[] parse(Stream<String> lines) throws ArrayException {
        if(lines == null) {
            String message = "parameter can't be null!";
            logger.error(message);
            throw new ArrayException(message);
        }
        int[] array = lines.filter(validator::isValid)
                .flatMap(line -> Arrays.stream(line.trim().split(", ")))
                .mapToInt(Integer::parseInt)
                .toArray();
        return array;
    }
}
