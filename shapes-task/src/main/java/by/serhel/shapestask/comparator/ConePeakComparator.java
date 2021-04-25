package by.serhel.shapestask.comparator;

import by.serhel.shapestask.entity.Cone;

import java.util.Comparator;

public class ConePeakComparator implements Comparator<Cone> {
    @Override
    public int compare(Cone o1, Cone o2) {
        double firstX = o1.getPeak().getX();
        double secondX = o2.getPeak().getX();
        return Double.compare(firstX, secondX);
    }
}
