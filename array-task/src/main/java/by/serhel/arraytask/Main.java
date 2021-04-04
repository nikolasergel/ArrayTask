package by.serhel.arraytask;

import by.serhel.arraytask.creator.ArrayCreator;
import by.serhel.arraytask.entity.Array;
import by.serhel.arraytask.exception.ArrayException;
import by.serhel.arraytask.parser.impl.ArrayParserImpl;
import by.serhel.arraytask.reader.impl.ArrayReaderImpl;
import by.serhel.arraytask.service.ArrayService;
import by.serhel.arraytask.service.impl.ArrayServiceImpl;
import by.serhel.arraytask.service.impl.ArrayServiceStream;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws ArrayException {
        ArrayCreator creator = new ArrayCreator(new ArrayReaderImpl(), new ArrayParserImpl());
        Array array = creator.createArray("arraysInput/array.txt");
        ArrayService arrayService = new ArrayServiceStream();
        System.out.println("max = " + arrayService.maxValue(array));
        System.out.println("min = " + arrayService.minValue(array));
        System.out.println("avg = " + arrayService.avg(array));
        System.out.println("sum = " + arrayService.sum(array));
        System.out.println("countOfPositiveNumbers = " + arrayService.countPositiveNumbers(array));
        System.out.println("countOfNegativeNumbers = " + arrayService.countNegativeNumbers(array));
        System.out.println("Array before replace: ");
        Arrays.stream(array.getArray())
                .forEach(element -> System.out.print(" " + element));
        System.out.println("\nArray after replace elements which equal 5 or -5");
        Arrays.stream(arrayService.replaceElementsByCondition(array, 999, (value -> value != 1000)).getArray())
                .forEach(element -> System.out.print(" " + element));

    }
}
