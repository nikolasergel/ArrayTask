package by.serhel.arraytask.service;

import by.serhel.arraytask.entity.Array;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;

public class StatisticService {
    private static final Logger logger = LogManager.getLogger();

    public StatisticService() {

    }

    public int avg(Array  array) {
        int sum = sum(array);
        int[] arrayBuff = array.getArray();
        return sum / arrayBuff.length;
    }

    public int sum(Array array) {
        int sum = 0;
        for (int element : array.getArray()) {
            sum += element;
        }
        return sum;
    }

    public int countPositiveNumbers(Array array) {
        return getCountOfNumbers((o1, o2) -> (o1 < o2 ? 1 : -1), array);
    }

    public int countNegativeNumbers(Array array) {
        return getCountOfNumbers((o1, o2) -> (o1 > o2 ? 1 : -1), array);
    }

    private int getCountOfNumbers(Comparator<Integer> comparator, Array array) {
        int count = 0;
        for (int element : array.getArray()) {
            if (comparator.compare(0, element) > 0) {
                count++;
            }
        }
        return count;
    }
}
