package by.serhel.parser;

import by.serhel.composite.Symbol;
import by.serhel.composite.TextElement;
import by.serhel.composite.TextElementType;

public class WordParser extends AbstractParser{
    public static String PUNCTUATION_REGEX = "[.:;?!,(.{3})]";
    public static String WORD_DELIMITER_REGEX = "(?=[.:;?!,(.{3})])|(?<=[.:;?!,(.{3})])";

    public WordParser(AbstractParser next) {
        super(next);
    }

    @Override
    public void parse(String text, TextElement element) {
        String[] parts = text.split(WORD_DELIMITER_REGEX);
        for(String part : parts){
            if(part.matches(PUNCTUATION_REGEX)){
                Symbol symbol = new Symbol(part, TextElementType.WORD);
                element.add(symbol);
            }
            else{
                TextElement word = new TextElement(TextElementType.WORD);
                next.parse(part, word);
                element.add(word);
            }
        }
    }
}
