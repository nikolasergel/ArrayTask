package by.serhel.shapestask.repository.impl;

import by.serhel.shapestask.entity.Cone;
import by.serhel.shapestask.exception.ShapeException;
import by.serhel.shapestask.repository.Specification;
import by.serhel.shapestask.service.ShapeService;
import by.serhel.shapestask.service.impl.ConeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class VolumeSpecification implements Specification<Cone> {
    public static final Logger logger= LogManager.getLogger();
    private double min;
    private double max;

    public VolumeSpecification(double min, double max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public boolean specify(Cone cone) {
        ShapeService service = new ConeService();
        double volume = 0;
        try {
            volume = service.calculateVolume(cone);
        } catch (ShapeException e) {
            logger.warn("Invalid cone", e);
        }
        return volume >= min && volume <= max;
    }
}
