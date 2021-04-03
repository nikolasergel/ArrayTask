package by.serhel.arraytask.service.impl;

import by.serhel.arraytask.entity.Array;
import by.serhel.arraytask.exception.ArrayException;
import by.serhel.arraytask.service.StatisticService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class StatisticServiceStream implements StatisticService {
    private static final Logger logger = LogManager.getLogger();

    public StatisticServiceStream() {

    }

    public int avg(Array array) throws ArrayException {
        if (array == null) {
            ArrayException exception = new ArrayException("maxValue: Array can't be null");
            logger.info(exception);
            throw exception;
        }
        int avg = sum(array) / array.getLength();
        return avg;
    }

    public int sum(Array array) throws ArrayException {
        if (array == null) {
            ArrayException exception = new ArrayException("sum: Array can't be null");
            logger.info(exception);
            throw exception;
        }
        int sum = Arrays.stream(array.getArray())
                .sum();
        return sum;
    }

    public int countPositiveNumbers(Array array) throws ArrayException {
        if (array == null) {
            ArrayException exception = new ArrayException("countPositiveNumbers: Array can't be null");
            logger.info(exception);
            throw exception;
        }
        int countPositive = (int)Arrays.stream(array.getArray())
                .filter(element -> element >= 0)
                .count();
        return countPositive;
    }

    public int countNegativeNumbers(Array array) throws ArrayException {
        if (array == null) {
            ArrayException exception = new ArrayException("countNegativeNumbers: Array can't be null");
            logger.info(exception);
            throw exception;
        }
        int countNegative = (int)Arrays.stream(array.getArray())
                .filter(element -> element < 0)
                .count();
        return countNegative;
    }
}
