package by.serhel.arraytask.entity;

import by.serhel.arraytask.exception.ArrayException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class Array{
    private static final Logger logger = LogManager.getLogger();
    private int[] array;

    public Array(int length) throws ArrayException {
        if(length < 1){
            ArrayException exception = new ArrayException("Array length must be greater then 0");
            logger.info(exception);
            throw exception;
        }
        this.array = new int[length];
    }

    public Array(int[] array) throws ArrayException {
        if(array == null){
            ArrayException exception = new ArrayException("Null Argument");
            logger.info(exception);
            throw exception;
        }
        this.array = array;
    }

//    public int[] getArray() {
//        logger.info("Array successfully returned");
//        return array;
//    }

    public int getLength(){
        logger.info("Array length successfully returned");
        return array.length;
    }

    public int getElement(int index) throws ArrayException {
        if(index < 0 || index >= array.length){
            ArrayException exception = new ArrayException("Illegal array index");
            logger.info(exception);
            throw exception;
        }
        return array[index];
    }

    public void setElement(int element, int index) throws ArrayException {
        if(index < 0 || index >= array.length){
            ArrayException exception = new ArrayException("Illegal array index");
            logger.info(exception);
            throw exception;
        }
        array[index] = element;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(array);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Array array1 = (Array) o;
        return Arrays.equals(array, array1.array);
    }
    
    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder("[ ");
        for(int element : array){
            builder.append(element);
        }
        builder.append("]");
        return builder.toString();
    }
}
