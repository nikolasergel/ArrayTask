package by.serhel.shapestask.observer;

import by.serhel.shapestask.creator.ConeCreator;
import by.serhel.shapestask.entity.Cone;
import by.serhel.shapestask.entity.ConeParameters;
import by.serhel.shapestask.observer.impl.ConeObserver;
import by.serhel.shapestask.warehouse.Warehouse;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNull;

public class ObserverTest {
    private Cone cone;
    private ConeCreator creator = new ConeCreator();
    private Warehouse warehouse = Warehouse.getInstance();
    private ConeObserver observer = new ConeObserver();

    @BeforeTest
    public void setUp(){
        double[] array = { 1, 1, 1, 4, 4, 4, 1};
        if(cone != null) {
            warehouse.remove(cone.getId());
        }
        cone = creator.createShape(array);
        cone.addObserver(observer);
    }

    @Test
    public void testNotifyObservers() {
        cone.setRadius(5);
        ConeParameters expected = warehouse.get(cone.getId());
        cone.setRadius(3);
        ConeParameters actual = warehouse.get(cone.getId());
        assertNotEquals(actual, expected);
    }

    @Test
    public void testRemoveObserver() {
        warehouse.remove(cone.getId());
        cone.removeObserver(observer);
        cone.setRadius(5);
        ConeParameters actual = warehouse.get(cone.getId());
        assertNull(actual);
    }

    @Test
    public void testAddObserver() {
        cone.removeObserver(observer);
        cone.setRadius(5);
        ConeParameters expected = warehouse.get(cone.getId());
        cone.addObserver(observer);
        cone.setRadius(5);
        ConeParameters actual = warehouse.get(cone.getId());
        assertNotEquals(actual, expected);
    }

    @Test
    public void testUpdate(){
        Cone buff = creator.createShape(new double[]{ 2.2, 3, 5, 8, 9, 7, 2});
        ConeParameters expected = warehouse.get(buff.getId());
        observer.update(buff);
        ConeParameters actual = warehouse.get(buff.getId());
        assertNotEquals(actual, expected);
        warehouse.remove(buff.getId());
    }
}