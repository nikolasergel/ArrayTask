package by.sergel._main;

import by.sergel.entity.Ship;
import by.sergel.util.ShipGenerator;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);
        for (Ship ship : ShipGenerator.generate(10)) {
            service.submit(ship);
        }
        service.shutdown();
    }
}
