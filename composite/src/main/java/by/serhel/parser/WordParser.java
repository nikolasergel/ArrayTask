package by.serhel.parser;

import by.serhel.composite.SymbolElement;
import by.serhel.composite.TextElement;
import by.serhel.composite.TextElementType;

public class WordParser extends AbstractParser {
    public static String WORD_REGEX = "\\w+";
    public static String WORD_DELIMITER_REGEX = "(?<=\\W)|(?=\\W)";

    public WordParser(AbstractParser next) {
        super(next);
    }

    @Override
    public void parse(String text, TextElement element) {
        String[] parts = text.split(WORD_DELIMITER_REGEX);
        for (String part : parts) {
            if(part.matches(WORD_REGEX)){
                TextElement word = new TextElement(TextElementType.WORD);
                element.add(word);
                next.parse(part, word);
            } else{
                SymbolElement symbol = new SymbolElement(part, TextElementType.SYMBOL);
                element.add(symbol);
            }
        }
    }
}
