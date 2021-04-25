package by.serhel.shapestask.validator;

import by.serhel.shapestask.entity.Cone;
import by.serhel.shapestask.entity.Point;

public class ConeValidator {
    public static boolean isValidCone(Cone cone){
        if(cone.getBase() == null || cone.getPeak() == null || cone.getRadius() <= 0){
            return false;
        }
        Point base = cone.getBase();
        Point peak = cone.getPeak();
        boolean isValid = base.getX() != peak.getX() || base.getY() != peak.getY() || base.getZ() != peak.getZ();
        return isValid;
    }
}
