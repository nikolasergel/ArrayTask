package by.serhel.arraytask;

import by.serhel.arraytask.creator.ArrayCreator;
import by.serhel.arraytask.creator.impl.ArrayCreatorImpl;
import by.serhel.arraytask.entity.Array;
import by.serhel.arraytask.parser.ArrayParser;
import by.serhel.arraytask.parser.impl.ArrayParserImpl;
import by.serhel.arraytask.reader.ArrayReader;
import by.serhel.arraytask.reader.impl.ArrayReaderImpl;
import by.serhel.arraytask.service.ArrayService;
import by.serhel.arraytask.service.impl.ArrayServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.stream.Stream;

public class Main {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        ArrayReader reader = new ArrayReaderImpl();
        ArrayParser parser = new ArrayParserImpl();
        ArrayCreator creator = new ArrayCreatorImpl();
        ArrayService arrayService = new ArrayServiceImpl();

        try{
            Stream<String> lines = reader.readToStream("data/array.txt");
            int[] numbers = parser.parse(lines);
            Array array = creator.createArray(numbers);

            System.out.println("max = " + arrayService.maxValue(array));
            System.out.println("min = " + arrayService.minValue(array));
            System.out.println("avg = " + arrayService.avg(array));
            System.out.println("sum = " + arrayService.sum(array));
            System.out.println("countOfPositiveNumbers = " + arrayService.countPositiveNumbers(array));
            System.out.println("countOfNegativeNumbers = " + arrayService.countNegativeNumbers(array));

            System.out.println("Array before replace: ");
            Arrays.stream(array.getArray())
                    .forEach(element -> System.out.print(" " + element));
            System.out.println("\nArray after replace elements which equal 5 or -5");
            Arrays.stream(arrayService.replaceElementsByCondition(array, 999, (value -> value != 1000)).getArray())
                    .forEach(element -> System.out.print(" " + element));

        }
        catch (Exception exception){
            logger.info("Exception thrown!", exception);
        }
    }
}
