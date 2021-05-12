package by.serhel.reader;

import by.serhel.exception.CustomFileException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CustomReader {
    public static String readText(String filepath) throws CustomFileException {
        String text;
        try {
            Path path = Paths.get(filepath);
            text = Files.readString(path);
        } catch (IOException e){
            throw new CustomFileException(e.getMessage(), e.getCause());
        }
        return text;
    }
}
