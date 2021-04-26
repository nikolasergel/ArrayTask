package by.serhel.shapestask.observer.impl;

import by.serhel.shapestask.entity.Cone;
import by.serhel.shapestask.entity.ConeParameters;
import by.serhel.shapestask.exception.ShapeException;
import by.serhel.shapestask.observer.Observer;
import by.serhel.shapestask.service.ShapeService;
import by.serhel.shapestask.service.impl.ConeService;
import by.serhel.shapestask.warehouse.Warehouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConeObserver implements Observer {
    public static final Logger logger = LogManager.getLogger();
    private Warehouse warehouse;
    private ShapeService service;

    public ConeObserver() {
        this.warehouse = Warehouse.getInstance();
        this.service = new ConeService();
    }

    @Override
    public void update(Cone cone) {
        double area = 0;
        double volume = 0;
        try {
            area = service.calculateArea(cone);
            volume = service.calculateVolume(cone);
            ConeParameters parameters = new ConeParameters(cone.getId(), area, volume);
            warehouse.put(cone.getId(), parameters);
        } catch (ShapeException e) {
            logger.error("Can't add parameters to warehouse!", e);
        }
    }
}
