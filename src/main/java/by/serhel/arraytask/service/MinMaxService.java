package by.serhel.arraytask.service;

import by.serhel.arraytask.entity.Array;
import by.serhel.arraytask.exception.ArrayException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;

public class MinMaxService {
    private static final Logger logger = LogManager.getLogger();

    public MinMaxService() {
    }

    public int maxValue(Array array) throws ArrayException {
        if (array == null) {
            ArrayException exception = new ArrayException("maxValue: Array can't be null");
            logger.info(exception);
            throw exception;
        }
        return findWithComparator((o1, o2) -> (o1 < o2 ? 1 : -1), array);
    }

    public int minValue(Array array) throws ArrayException {
        if (array == null) {
            ArrayException exception = new ArrayException("minValue: Array can't be null");
            logger.info(exception);
            throw exception;
        }
        return findWithComparator((o1, o2) -> (o1 > o2 ? 1 : -1), array);
    }

    private int findWithComparator(Comparator<Integer> comparator, Array array) throws ArrayException {
        int value = Integer.MIN_VALUE;
        value = array.getElement(0);
        for (int i = 0; i < array.getLength(); i++) {
            int buff = array.getElement(i);
            if (comparator.compare(value, buff) > 0) {
                value = buff;
            }
        }
        return value;
    }
}
