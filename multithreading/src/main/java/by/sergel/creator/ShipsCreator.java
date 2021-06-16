package by.sergel.creator;

import by.sergel.entity.Ship;
import by.sergel.parser.CustomParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShipsCreator {
    public List<Ship> createShips(List<String> lines){
        List<Ship> shipList = new ArrayList<>();
        CustomParser parser = new CustomParser();
        for(String line : lines){
            Optional<Object[]> optional = parser.parseShipData(line);
            if(optional.isPresent()){
                Object[] params = optional.get();
                Ship ship = new Ship((Integer)params[0], (Integer)params[1], (Ship.Task) params[2]);
                shipList.add(ship);
            }
        }
        return shipList;
    }
}
