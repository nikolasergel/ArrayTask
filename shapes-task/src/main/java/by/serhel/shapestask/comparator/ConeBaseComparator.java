package by.serhel.shapestask.comparator;

import by.serhel.shapestask.entity.Cone;

import java.util.Comparator;

public class ConeBaseComparator implements Comparator<Cone> {
    @Override
    public int compare(Cone o1, Cone o2) {
        double firstX = o1.getBase().getX();
        double secondX = o2.getBase().getX();
        return Double.compare(firstX, secondX);
    }
}
