package by.serhel.parser;

import by.serhel.composite.Symbol;
import by.serhel.composite.TextElement;
import by.serhel.composite.TextElementType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser extends AbstractParser{
    public static String SENTENCE_REGEX = "[.!?(.{3})]";

    public SentenceParser(AbstractParser next, TextElement element) {
        super(next);
    }

    @Override
    public TextElement parse(String text) {
        element = new TextElement(TextElementType.SENTENCE);
        Pattern pattern = Pattern.compile(SENTENCE_REGEX);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()){
            element.addElement(next.parse(text.substring(matcher.start(), matcher.end())));
            element.addElement(new Symbol(new Character(text.charAt(matcher.end())).toString(), TextElementType.SYMBOL));
        }
        if () {
            return element;
        }
    }
}
