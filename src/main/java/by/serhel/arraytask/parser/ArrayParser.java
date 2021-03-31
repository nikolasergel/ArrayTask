package by.serhel.arraytask.parser;

import by.serhel.arraytask.exception.ArrayException;

import java.util.stream.Stream;

public interface ArrayParser {
    int[] parse(Stream<String> lines) throws ArrayException;
}
