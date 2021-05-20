package by.serhel.parser;

import by.serhel.composite.Symbol;
import by.serhel.composite.TextElement;
import by.serhel.composite.TextElementType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser extends AbstractParser{
    public static String SENTENCE_REGEX = "(?=[.!?(.{3})])";

    public SentenceParser(AbstractParser next) {
        super(next);
    }

    @Override
    public void parse(String text, TextElement element) {
        String[] parts = text.split(SENTENCE_REGEX);
        for(String part : parts){
            TextElement sentence = new TextElement(TextElementType.SENTENCE);
            next.parse(part, sentence);
            element.addElement(sentence);
        }
    }
}
