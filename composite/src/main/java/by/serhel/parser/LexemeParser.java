package by.serhel.parser;

import by.serhel.composite.Symbol;
import by.serhel.composite.TextElement;
import by.serhel.composite.TextElementType;

public class LexemeParser extends AbstractParser {
    public static String WHITESPACE_REGEX = "[\t\\s]+";
    public static String LEXEME_REGEX = "(?=[\t\\s]+)|(?<=[\t\\s])";

    public LexemeParser(AbstractParser next) {
        super(next);
    }

    @Override
    public void parse(String text, TextElement element) {
        String[] parts = text.split(LEXEME_REGEX);
        for (String part : parts) {
            if (part.matches(WHITESPACE_REGEX)) {
                Symbol symbol = new Symbol(part, TextElementType.PUNCTUATION);
                element.add(symbol);
            } else {
                TextElement lexeme = new TextElement(TextElementType.LEXEME);
                next.parse(part, lexeme);
                element.add(lexeme);
            }
        }
    }
}
