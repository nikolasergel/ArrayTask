package by.serhel.arraytask.service;

import by.serhel.arraytask.entity.Array;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;

public class MinMaxService {
    private static final Logger logger = LogManager.getLogger();

    public MinMaxService() {
    }

    public int maxValue(Array array) {
        return findWithComparator((o1, o2) -> (o1 < o2 ? 1 : -1), array);
    }

    public int minValue(Array array) {
        return findWithComparator((o1, o2) -> (o1 > o2 ? 1 : -1), array);
    }

    private int findWithComparator(Comparator<Integer> comparator, Array array) {
        int[] arrayBuff = array.getArray();
        int value = arrayBuff[0];
        for (int element : arrayBuff) {
            if (comparator.compare(value, element) > 0) {
                value = element;
            }
        }
        return value;
    }
}
