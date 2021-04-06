package by.serhel.arraytask.service.impl;

import by.serhel.arraytask.entity.Array;
import by.serhel.arraytask.exception.ArrayException;
import by.serhel.arraytask.service.ArrayService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;
import java.util.function.IntFunction;

public class ArrayServiceImpl implements ArrayService {
    private static final Logger logger = LogManager.getLogger();

    public ArrayServiceImpl() {

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

    @Override
    public Array replaceElementsByCondition(Array array, int value, IntFunction<Boolean> statement) throws ArrayException {
        if (array == null || statement == null) {
            ArrayException exception = new ArrayException("function parameters can't be null!");
            logger.info(exception);
            throw exception;
        }
        Array arrayCopy = new Array(array.getArray());
        for(int i = 0; i < arrayCopy.getLength(); i++){
            if(statement.apply(array.getElement(i))){
                arrayCopy.setElement(value, i);
            }
        }
        return arrayCopy;
    }

    private int findWithComparator(Comparator<Integer> comparator, Array array) throws ArrayException {
        int value = array.getElement(0);
        for (int i = 0; i < array.getLength(); i++) {
            int buff = array.getElement(i);
            if (comparator.compare(value, buff) > 0) {
                value = buff;
            }
        }
        return value;
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
