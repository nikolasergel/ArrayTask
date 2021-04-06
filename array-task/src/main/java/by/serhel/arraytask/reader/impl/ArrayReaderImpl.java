package by.serhel.arraytask.reader.impl;

import by.serhel.arraytask.exception.ResourceFileException;
import by.serhel.arraytask.reader.ArrayReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class ArrayReaderImpl implements ArrayReader {
    private static final Logger logger = LogManager.getLogger();

    public Stream<String> readToStream(String path) throws ResourceFileException {
        if(path == null || path.isEmpty()){
            String message = "Path can't be null or empty.";
            logger.error(message);
            throw new ResourceFileException(message);
        }
        Stream<String> lines;
        try{
            URL url = getClass().getClassLoader().getResource(path);
            if(url == null){
                String message = "Path to resource file is incorrect!";
                logger.error(message);
                throw new ResourceFileException(message);
            }
            Path pathFile = Paths.get(url.getPath());
            lines = Files.lines(pathFile);
        }
        catch (IOException ioException){
            String message = ioException.getMessage();
            logger.error(message);
            throw new ResourceFileException(message);
        }
        return lines;
    }
}
