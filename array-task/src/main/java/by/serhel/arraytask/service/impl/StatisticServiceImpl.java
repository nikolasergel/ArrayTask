package by.serhel.arraytask.service.impl;

import by.serhel.arraytask.entity.Array;
import by.serhel.arraytask.exception.ArrayException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;

public class StatisticServiceImpl implements by.serhel.arraytask.service.StatisticService {
    private static final Logger logger = LogManager.getLogger();

    public StatisticServiceImpl() {

    }

    public int avg(Array  array) throws ArrayException {
        if (array == null) {
            ArrayException exception = new ArrayException("maxValue: Array can't be null");
            logger.info(exception);
            throw exception;
        }
        int sum = sum(array);
        return sum / array.getLength();
    }

    public int sum(Array array) throws ArrayException {
        if (array == null) {
            ArrayException exception = new ArrayException("sum: Array can't be null");
            logger.info(exception);
            throw exception;
        }
        int sum = 0;
        for (int i = 0; i < array.getLength(); i++) {
            sum += array.getElement(i);
        }
        return sum;
    }

    public int countPositiveNumbers(Array array) throws ArrayException {
        if (array == null) {
            ArrayException exception = new ArrayException("sum: Array can't be null");
            logger.info(exception);
            throw exception;
        }
        return getCountOfNumbers((o1, o2) -> (o1 < o2 ? 1 : -1), array);
    }

    public int countNegativeNumbers(Array array) throws ArrayException {
        if (array == null) {
            ArrayException exception = new ArrayException("sum: Array can't be null");
            logger.info(exception);
            throw exception;
        }
        return getCountOfNumbers((o1, o2) -> (o1 > o2 ? 1 : -1), array);
    }

    private int getCountOfNumbers(Comparator<Integer> comparator, Array array) throws ArrayException {
        int count = 0;
        for (int i = 0; i < array.getLength(); i++) {
            int buff = array.getElement(i);
            if (comparator.compare(0, buff) > 0) {
                count++;
            }
        }
        return count;
    }
}
