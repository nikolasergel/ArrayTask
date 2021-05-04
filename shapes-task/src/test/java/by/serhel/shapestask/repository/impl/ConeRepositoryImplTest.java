package by.serhel.shapestask.repository.impl;

import by.serhel.shapestask.entity.Cone;
import by.serhel.shapestask.entity.Point;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

public class ConeRepositoryImplTest {
    private ConeRepository repository = ConeRepository.getInstance();
    private  Cone expectedCone;

    @BeforeTest
    public void setUp(){
        expectedCone = new Cone(1, new Point(1, 1, 1), new Point(4, 4, 4), 3);
        repository.add(expectedCone);
    }

    @AfterTest
    public void reset(){
        repository.remove(expectedCone);
    }

    @Test
    public void testGetById() {
        Cone actual = repository.getById(expectedCone.getId());
        assertEquals(actual, expectedCone);
    }

    @Test
    public void testGetAll() {
        List<Cone> expected = new ArrayList<>();
        Cone cone = new Cone(2, new Point(2, 2.2, 5), new Point(5, 6, 7), 9);
        expected.add(expectedCone);
        expected.add(cone);
        repository.add(cone);
        List<Cone> actual = repository.getAll();
        assertEquals(actual, expected);
    }

    @Test
    public void testRemove() {
        repository.remove(expectedCone);
        boolean actual = repository.getAll().contains(expectedCone);
        assertFalse(actual);
    }
}