package by.serhel.shapestask.reader;

import by.serhel.shapestask.exception.CustomFileException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class CustomFileReader {

    public Stream<String> readLines(String filePath) throws CustomFileException {
        Path path = Paths.get(filePath);
        Stream<String> lines = null;
        try {
            lines = Files.lines(path);
        } catch (IOException e) {
            throw new CustomFileException("File reading was failed: " + path, e);
        }
        return lines;
    }
}

