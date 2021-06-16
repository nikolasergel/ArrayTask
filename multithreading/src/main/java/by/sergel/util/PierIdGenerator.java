package by.sergel.util;

public class PierIdGenerator {
    private static long count;

    public static long generateId(){
        return ++count;
    }
}
