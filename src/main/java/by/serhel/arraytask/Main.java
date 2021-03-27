package by.serhel.arraytask;

import by.serhel.arraytask.entity.Array;
import by.serhel.arraytask.service.MinMaxService;
import by.serhel.arraytask.service.StatisticService;

public class Main {

    public static void main(String[] args) {
        Array array = new Array(new int[]{1, 5, -1, 6, -10, 2, 3, 15});
        MinMaxService minMaxService = new MinMaxService();
        StatisticService statisticService = new StatisticService();
        System.out.println("MinMaxService: max = " + minMaxService.maxValue(array));
        System.out.println("MinMaxService: min = " + minMaxService.minValue(array));
        System.out.println();
        System.out.println("StatisticService: avg = " + statisticService.avg(array));
        System.out.println("StatisticService: sum = " + statisticService.sum(array));
        System.out.println("StatisticService: countOfPositiveNumbers = " + statisticService.countPositiveNumbers(array));
        System.out.println("StatisticService: countOfNegativeNumbers = " + statisticService.countNegativeNumbers(array));
    }
}
