package by.sergel.epam;

import by.sergel.epam.entity.Array;
import by.sergel.epam.service.MinMaxService;
import by.sergel.epam.service.StatisticService;

public class Main {

    public static void main(String[] args) {
        Array array = new Array(new int[]{1, 5, -1, 6, -10, 2, 3, 15});
        MinMaxService minMaxService = new MinMaxService(array);
        StatisticService statisticService = new StatisticService(array);
        System.out.println("MinMaxService: max = " + minMaxService.max());
        System.out.println("MinMaxService: min = " + minMaxService.min());
        System.out.println();
        System.out.println("StatisticService: avg = " + statisticService.avg());
        System.out.println("StatisticService: sum = " + statisticService.sum());
        System.out.println("StatisticService: countOfPositiveNumbers = " + statisticService.countPositiveNumbers());
        System.out.println("StatisticService: countOfNegativeNumbers = " + statisticService.countNegativeNumbers());
    }
}
