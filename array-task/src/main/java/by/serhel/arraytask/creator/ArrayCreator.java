package by.serhel.arraytask.creator;

import by.serhel.arraytask.entity.Array;
import by.serhel.arraytask.exception.ArrayException;

public interface ArrayCreator {
    Array createArray(int[] array) throws ArrayException;
}
