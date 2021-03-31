package by.serhel.arraytask;

import by.serhel.arraytask.creator.ArrayCreator;
import by.serhel.arraytask.entity.Array;
import by.serhel.arraytask.exception.ArrayException;
import by.serhel.arraytask.parser.impl.ArrayParserImpl;
import by.serhel.arraytask.reader.impl.ArrayReaderImpl;
import by.serhel.arraytask.service.MinMaxService;
import by.serhel.arraytask.service.StatisticService;
import by.serhel.arraytask.service.impl.MinMaxServiceImpl;
import by.serhel.arraytask.service.impl.StatisticServiceImpl;

public class Main {

    public static void main(String[] args) throws ArrayException {
        ArrayCreator creator = new ArrayCreator(new ArrayReaderImpl(), new ArrayParserImpl());
        Array array = creator.createArray("arraysInput/array.txt");
        MinMaxService minMaxService = new MinMaxServiceImpl();
        StatisticService statisticService = new StatisticServiceImpl();
        System.out.println("MinMaxService: max = " + minMaxService.maxValue(array));
        System.out.println("MinMaxService: min = " + minMaxService.minValue(array));
        System.out.println();
        System.out.println("StatisticService: avg = " + statisticService.avg(array));
        System.out.println("StatisticService: sum = " + statisticService.sum(array));
        System.out.println("StatisticService: countOfPositiveNumbers = " + statisticService.countPositiveNumbers(array));
        System.out.println("StatisticService: countOfNegativeNumbers = " + statisticService.countNegativeNumbers(array));
    }
}
