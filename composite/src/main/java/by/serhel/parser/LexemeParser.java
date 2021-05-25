package by.serhel.parser;

import by.serhel.composite.SymbolElement;
import by.serhel.composite.TextElement;
import by.serhel.composite.TextElementType;

public class LexemeParser extends AbstractParser {
    public static String WHITESPACE_REGEX = "\\s+";
    public static String LEXEME_REGEX = "(?=\\s+)|(?<=\\s)";

    public LexemeParser(AbstractParser next) {
        super(next);
    }

    @Override
    public void parse(String text, TextElement element) {
        String[] parts = text.split(LEXEME_REGEX);
        for (String part : parts) {
            if (part.matches(WHITESPACE_REGEX)) {
                SymbolElement symbol = new SymbolElement(part, TextElementType.WHITESPACE);
                element.add(symbol);
            } else {
                TextElement lexeme = new TextElement(TextElementType.LEXEME);
                element.add(lexeme);
                next.parse(part, lexeme);
            }
        }
    }
}
