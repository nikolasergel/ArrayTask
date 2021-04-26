package by.serhel.shapestask.warehouse;

import by.serhel.shapestask.entity.ConeParameters;

import java.util.HashMap;
import java.util.Map;

public class Warehouse {
    private static Warehouse instance;
    private Map<Integer,ConeParameters> parametersMap;

    private Warehouse() {
        this.parametersMap = new HashMap<>();
    }

    public static Warehouse getInstance() {
        if(instance == null){
            instance = new Warehouse();
        }
        return instance;
    }

    public void put(int id, ConeParameters parameters){
        parametersMap.put(id, parameters);
    }

    public void update(int id, ConeParameters parameters){
        remove(id);
        put(id, parameters);
    }

    public void remove(int id){
        parametersMap.remove(id);
    }
}
