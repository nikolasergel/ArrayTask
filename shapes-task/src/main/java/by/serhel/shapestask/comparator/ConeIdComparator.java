package by.serhel.shapestask.comparator;

import by.serhel.shapestask.entity.Cone;

import java.util.Comparator;

public class ConeIdComparator implements Comparator<Cone> {
    @Override
    public int compare(Cone o1, Cone o2) {
        int firstId = o1.getId();
        int secondId = o2.getId();
        return Integer.compare(firstId, secondId);
    }
}
