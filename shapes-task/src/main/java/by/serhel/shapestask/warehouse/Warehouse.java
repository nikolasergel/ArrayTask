package by.serhel.shapestask.warehouse;

import by.serhel.shapestask.entity.Cone;
import by.serhel.shapestask.entity.ConeParameters;
import by.serhel.shapestask.exception.ShapeException;
import by.serhel.shapestask.observer.Observer;
import by.serhel.shapestask.service.impl.ConeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Warehouse implements Observer {
    private static final Logger logger = LogManager.getLogger();
    private static Warehouse instance;
    private Set<ConeParameters> parametersSet;
    private ConeService service;

    private Warehouse() {
        this.parametersSet = new HashSet<>();
        this.service = new ConeService();
    }

    public static Warehouse getInstance() {
        if(instance == null){
            instance = new Warehouse();
        }
        return instance;
    }

    public void add(Cone cone){
        double area = 0;
        double volume = 0;
        try {
            area = service.calculateArea(cone);
            volume = service.calculateVolume(cone);
            ConeParameters parameters = new ConeParameters(cone.getId(), area, volume);
            parametersSet.add(parameters);
        } catch (ShapeException e) {
            logger.error("Can't add parameters to warehouse!", e);
        }
    }

    @Override
    public void update(Cone cone){
        removeById(cone.getId());
        add(cone);
    }

    public void removeById(int id){
        parametersSet = parametersSet.stream()
                .filter(param -> param.getId() != id)
                .collect(Collectors.toSet());
    }

    public void remove(ConeParameters parameters){
        parametersSet.remove(parameters);
    }
}
