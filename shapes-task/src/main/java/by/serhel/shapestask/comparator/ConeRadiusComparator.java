package by.serhel.shapestask.comparator;

import by.serhel.shapestask.entity.Cone;

import java.util.Comparator;

public class ConeRadiusComparator implements Comparator<Cone> {
    @Override
    public int compare(Cone o1, Cone o2) {
        double firstRadius = o1.getRadius();
        double secondRadius = o2.getRadius();
        return Double.compare(firstRadius, secondRadius);
    }
}
