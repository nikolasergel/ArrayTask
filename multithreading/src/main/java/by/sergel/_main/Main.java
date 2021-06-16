package by.sergel._main;

import by.sergel.creator.ShipsCreator;
import by.sergel.entity.Ship;
import by.sergel.exception.ResourceException;
import by.sergel.reader.CustomReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        String filepath = "data/ships.txt";
        CustomReader reader = new CustomReader();
        ShipsCreator creator = new ShipsCreator();
        List<String> lines;

        try {
            lines = reader.read(filepath);
        } catch (ResourceException e) {
            logger.error("Can't read data from file by filepath: " + filepath, e);
            return;
        }
        List<Ship> shipList = creator.createShips(lines);
        ExecutorService service = Executors.newFixedThreadPool(shipList.size());
        for (Ship ship : shipList) {
            service.submit(ship);
        }
        service.shutdown();
    }
}
