package by.serhel.arraytask.service;

import by.serhel.arraytask.entity.Array;
import by.serhel.arraytask.exception.ArrayException;

public interface MinMaxService {
    int minValue(Array array) throws ArrayException;
    int maxValue(Array array) throws ArrayException;
}
