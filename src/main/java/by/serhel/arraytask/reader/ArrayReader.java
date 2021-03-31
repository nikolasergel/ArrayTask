package by.serhel.arraytask.reader;

import by.serhel.arraytask.exception.ResourceFileException;

import java.util.stream.Stream;

public interface ArrayReader {
    Stream<String> readToStream(String path) throws ResourceFileException;
}
