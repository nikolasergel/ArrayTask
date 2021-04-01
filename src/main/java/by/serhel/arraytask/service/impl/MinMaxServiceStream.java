package by.serhel.arraytask.service.impl;

import by.serhel.arraytask.entity.Array;
import by.serhel.arraytask.exception.ArrayException;
import by.serhel.arraytask.service.MinMaxService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class MinMaxServiceStream implements MinMaxService {
    private static final Logger logger = LogManager.getLogger();

    public MinMaxServiceStream() {
    }

    public int maxValue(Array array) throws ArrayException {
        if (array == null) {
            ArrayException exception = new ArrayException("maxValue: Array can't be null");
            logger.info(exception);
            throw exception;
        }
        int max = Arrays.stream(array.getArray())
                .max()
                .getAsInt();
        return max;
    }

    public int minValue(Array array) throws ArrayException {
        if (array == null) {
            ArrayException exception = new ArrayException("minValue: Array can't be null");
            logger.info(exception);
            throw exception;
        }
        int min = Arrays.stream(array.getArray())
                .min()
                .getAsInt();
        return min;
    }
}
