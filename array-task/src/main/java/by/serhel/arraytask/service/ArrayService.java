package by.serhel.arraytask.service;

import by.serhel.arraytask.entity.Array;
import by.serhel.arraytask.exception.ArrayException;

import java.util.function.IntFunction;

public interface ArrayService {
    int avg(Array array) throws ArrayException;
    int sum(Array array) throws ArrayException;
    int countNegativeNumbers(Array array) throws ArrayException;
    int countPositiveNumbers(Array array) throws ArrayException;
    int minValue(Array array) throws ArrayException;
    int maxValue(Array array) throws ArrayException;
    Array replaceElementsByCondition(Array array, int value, IntFunction<Boolean> statement) throws ArrayException;
}
