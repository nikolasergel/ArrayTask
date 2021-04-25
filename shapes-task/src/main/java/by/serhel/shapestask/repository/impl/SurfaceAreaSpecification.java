package by.serhel.shapestask.repository.impl;

import by.serhel.shapestask.entity.Cone;
import by.serhel.shapestask.exception.ShapeException;
import by.serhel.shapestask.repository.Specification;
import by.serhel.shapestask.service.ShapeService;
import by.serhel.shapestask.service.impl.ConeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SurfaceAreaSpecification implements Specification<Cone> {
    public static final Logger logger= LogManager.getLogger();
    private double min;
    private double max;

    public SurfaceAreaSpecification(double min, double max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public boolean specify(Cone cone) {
        ShapeService service = new ConeService();
        double area = 0;
        try {
            area = service.calculateArea(cone);
        } catch (ShapeException e) {
            logger.warn("Invalid cone", e);
        }
        return area >= min && area <= max;
    }
}
