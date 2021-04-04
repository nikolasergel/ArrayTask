package by.serhel.arraytask;

import by.serhel.arraytask.creator.ArrayCreator;
import by.serhel.arraytask.entity.Array;
import by.serhel.arraytask.exception.ArrayException;
import by.serhel.arraytask.parser.impl.ArrayParserImpl;
import by.serhel.arraytask.reader.impl.ArrayReaderImpl;
import by.serhel.arraytask.service.ArrayService;
import by.serhel.arraytask.service.impl.ArrayServiceImpl;

public class Main {

    public static void main(String[] args) throws ArrayException {
        ArrayCreator creator = new ArrayCreator(new ArrayReaderImpl(), new ArrayParserImpl());
        Array array = creator.createArray("arraysInput/array.txt");
        ArrayService arrayService = new ArrayServiceImpl();
        System.out.println("MinMaxService: max = " + arrayService.maxValue(array));
        System.out.println("MinMaxService: min = " + arrayService.minValue(array));
        System.out.println();
        System.out.println("StatisticService: avg = " + arrayService.avg(array));
        System.out.println("StatisticService: sum = " + arrayService.sum(array));
        System.out.println("StatisticService: countOfPositiveNumbers = " + arrayService.countPositiveNumbers(array));
        System.out.println("StatisticService: countOfNegativeNumbers = " + arrayService.countNegativeNumbers(array));
    }
}
