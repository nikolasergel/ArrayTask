package by.sergel.parser;

import by.sergel.entity.Ship;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class CustomParser {
    public static final String WHITESPACE_REGEX = "\\s+";
    private static final Logger logger = LogManager.getLogger();

    public Optional<Object[]> parseShipData(String line) {
        Optional<Object[]> optional = Optional.empty();
        String[] strShipData = line.split(WHITESPACE_REGEX);
        Object[] shipData = new Object[3];
        try {
            int size = Integer.parseInt(strShipData[0]);
            int curSize = Integer.parseInt(strShipData[1]);
            shipData[0] = size;
            shipData[1] = curSize;
            shipData[2] = Ship.Task.valueOf(strShipData[2]);
            if(curSize >= 0 && size >= curSize){
                optional = Optional.of(shipData);
            }
        } catch (IllegalArgumentException e) {
            logger.error("Can't parse ship data: " + line);
        }
        return optional;
    }
}
