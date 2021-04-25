package by.serhel.shapestask.repository.impl;

import by.serhel.shapestask.entity.Cone;
import by.serhel.shapestask.entity.Point;
import by.serhel.shapestask.repository.Specification;

public class RangeSpecification implements Specification<Cone> {
    private int x;
    private int y;
    private int z;

    public RangeSpecification(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public boolean specify(Cone cone) {
        Point peak = cone.getPeak();
        Point base = cone.getBase();
        boolean isInRange = peak.getX() <= x && peak.getX() >= 0
                && peak.getY() <= x && peak.getY() >= 0
                && peak.getZ() <= x && peak.getZ() >= 0
                && base.getX() <= x && base.getX() >= 0
                && base.getY() <= x && base.getY() >= 0
                && base.getZ() <= x && base.getZ() >= 0;
        return isInRange;
    }
}
