package by.sergel.reader;

import by.sergel.exception.ResourceException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CustomReader {
    public List<String> read(String filepath) throws ResourceException {
        Path path = Paths.get(filepath);
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.lines(path).collect(Collectors.toList());
        } catch (IOException e){
            throw new ResourceException("Can't read or find file! Filepath: " + filepath, e);
        }
        return lines;
    }
}
