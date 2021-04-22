package by.serhel.shapestask.reader;

import by.serhel.shapestask.exception.CustomFileException;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class CustomFileReader {
    public Stream<String> readLines(String filePath) throws CustomFileException {
        filePath = getAbsolutePath(filePath);
        Path path = Paths.get(filePath);
        Stream<String> lines = null;
        try {
            lines = Files.lines(path);
        } catch (IOException e) {
            throw new CustomFileException("File reading was failed: " + path, e);
        }
        return lines;
    }

    public String getAbsolutePath(String path) throws CustomFileException {
        ClassLoader loader = getClass().getClassLoader();
        URL url = loader.getResource(path);
        if(url == null){
            throw new CustomFileException("Path is not found: " + path);
        }
        return url.getPath();
    }
}

