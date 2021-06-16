package by.sergel.util;

public class ShipIdGenerator {
    private static long count;

    public static long generateId(){
        return ++count;
    }
}
