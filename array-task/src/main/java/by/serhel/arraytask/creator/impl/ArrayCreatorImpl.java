package by.serhel.arraytask.creator.impl;

import by.serhel.arraytask.creator.ArrayCreator;
import by.serhel.arraytask.entity.Array;
import by.serhel.arraytask.exception.ArrayException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ArrayCreatorImpl implements ArrayCreator {
    private static final Logger logger = LogManager.getLogger();

    public ArrayCreatorImpl(){
    }
    
    public Array createArray(int[] array) throws ArrayException {
        Array arrayBuff = new Array(array);
        return arrayBuff;
    }
}
