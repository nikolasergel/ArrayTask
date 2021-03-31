package by.serhel.arraytask.service.impl;

import by.serhel.arraytask.entity.Array;
import by.serhel.arraytask.exception.ArrayException;
import by.serhel.arraytask.service.SortService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SortServiceImpl implements SortService {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void bubbleSort(Array array) throws ArrayException {
        if(array == null){
            String message = "Array can't be null";
            logger.error(message);
            throw new ArrayException(message);
        }
        for(int i = array.getLength() - 1 ; i > 0 ; i--) {
            for (int j = 0; j < i; j++) {
                if (array.getElement(j) > array.getElement(j + 1)) {
                    swap(array, j, j + 1);
                }
            }
        }
    }

    @Override
    public void quickSort(Array array, int begin, int end) throws ArrayException {
        if(array == null){
            String message = "Array can't be null";
            logger.error(message);
            throw new ArrayException(message);
        }
        if (begin < end) {
            int partitionIndex = partition(array, begin, end);
            quickSort(array, begin, partitionIndex - 1);
            quickSort(array, partitionIndex + 1, end);
        }
    }

    @Override
    public void InsertionSort(Array array) throws ArrayException {
        if(array == null){
            String message = "Array can't be null";
            logger.error(message);
            throw new ArrayException(message);
        }
        for (int i = 1; i < array.getLength(); i++) {
            int key = array.getElement(i);
            int j = i - 1;
            while (j >= 0 && array.getElement(j) > key) {
                array.setElement(array.getElement(j), j + 1);
                j = j - 1;
            }
            array.setElement(key, j + 1);
        }
    }

    private int partition(Array array, int begin, int end) throws ArrayException {
        int pivot = array.getElement(end);
        int i = begin - 1;

        for (int j = begin; j < end; j++) {
            if (array.getElement(j) <= pivot) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, end);

        return i+1;
    }

    private void swap(Array array, int i, int j) throws ArrayException {
        int buff = array.getElement(i);
        array.setElement(array.getElement(j), i);
        array.setElement(buff, j);
    }
}
