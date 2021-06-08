package by.sergel.util;

import by.sergel.entity.Ship;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ShipGenerator {
    private static final int MIN_SIZE_BOUND = 25;
    private static final int MAX_SIZE_BOUND = 100;

    public static List<Ship> generate(int amount) {
        List<Ship> shipsList = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            Random random = new Random();
            int size = random.nextInt(MAX_SIZE_BOUND) + MIN_SIZE_BOUND;
            Ship ship = new Ship(size, random.nextInt(size), random.nextBoolean() ? Ship.Task.LOAD : Ship.Task.UNLOAD);
            shipsList.add(ship);
        }
        return shipsList;
    }
}
