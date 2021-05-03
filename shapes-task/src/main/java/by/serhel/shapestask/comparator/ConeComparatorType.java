package by.serhel.shapestask.comparator;

import by.serhel.shapestask.entity.Cone;

import java.util.Comparator;

public enum ConeComparatorType implements Comparator<Cone> {
    ID{
        @Override
        public int compare(Cone o1, Cone o2) {
            int firstId = o1.getId();
            int secondId = o2.getId();
            return Integer.compare(firstId, secondId);
        }
    },
    BASE{
        @Override
        public int compare(Cone o1, Cone o2) {
            double firstX = o1.getBase().getX();
            double secondX = o2.getBase().getX();
            return Double.compare(firstX, secondX);
        }
    },
    PEAK{
        @Override
        public int compare(Cone o1, Cone o2) {
            double firstX = o1.getPeak().getX();
            double secondX = o2.getPeak().getX();
            return Double.compare(firstX, secondX);
        }
    },
    RADIUS{
        @Override
        public int compare(Cone o1, Cone o2) {
            double firstRadius = o1.getRadius();
            double secondRadius = o2.getRadius();
            return Double.compare(firstRadius, secondRadius);
        }
    };
}
