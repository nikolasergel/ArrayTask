package by.serhel.arraytask.service;

import by.serhel.arraytask.entity.Array;
import by.serhel.arraytask.exception.ArrayException;

public interface StatisticService {
    int avg(Array array) throws ArrayException;
    int sum(Array array) throws ArrayException;
    int countNegativeNumbers(Array array) throws ArrayException;
    int countPositiveNumbers(Array array) throws ArrayException;
}
