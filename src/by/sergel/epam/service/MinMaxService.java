package by.sergel.epam.service;

import by.sergel.epam.entity.Array;

import java.util.Comparator;

public class FindService {
    private Array array;

    public FindService(){

    }

    public FindService(int[] arr){
        this.array = new Array(arr);
    }

    private int findWithComparator(Comparator<Integer> comparator){
        int[] arr = array.getArray();
        int buff = arr[0];
        for(int element : array.getArray()){
            if(comparator.compare(buff, element) > 0){
                buff = element;
            }
        }
        return buff;
    }

    public int max(){
        return findWithComparator(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 > o2 ? 1 : -1;
            }
        });
    }

    public int min(){
        return findWithComparator(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 < o2 ? 1 : -1;
            }
        });
    }
}
