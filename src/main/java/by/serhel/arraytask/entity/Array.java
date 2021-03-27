package by.serhel.arraytask.entity;

import java.util.Arrays;

public class Array {
    private int[] array;

    public Array(int length){
        this.array = new int[length];
    }

    public Array(int[] arr){
        this.array = arr;
    }

    public int[] getArray() {
        return array;
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
