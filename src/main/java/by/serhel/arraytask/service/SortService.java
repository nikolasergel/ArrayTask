package by.serhel.arraytask.service;

import by.serhel.arraytask.entity.Array;
import by.serhel.arraytask.exception.ArrayException;

public interface SortService {
    void bubbleSort(Array array) throws ArrayException;
    void quickSort(Array array, int begin, int end) throws ArrayException;
    void InsertionSort(Array array) throws ArrayException;
}
