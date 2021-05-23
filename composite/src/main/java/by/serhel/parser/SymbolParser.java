package by.serhel.parser;

import by.serhel.composite.Symbol;
import by.serhel.composite.TextElement;
import by.serhel.composite.TextElementType;

public class SymbolParser extends AbstractParser{
    public static String VOWEL_REGEX = "[aeiouyAEIOUY]";
    public static String CONSONANT_REGEX = "[qwrtpsdfghjklzxcvbnmQWRTPSDFGHJKLZXCVBNM]";
    public static String DIGIT_REGEX = "[0-9]+";
    public static String SYMBOL_DELIMITER_REGEX = "(?!^)";

    public SymbolParser(AbstractParser next) {
        super(next);
    }

    @Override
    public void parse(String text, TextElement element) {
        String[] parts = text.split(SYMBOL_DELIMITER_REGEX);
        for(String part : parts){
            Symbol symbol = new Symbol(part);
            TextElementType type;
            if(part.matches(VOWEL_REGEX)){
                type = TextElementType.VOWEL;
            }
            else if(part.matches(CONSONANT_REGEX)){
                type = TextElementType.CONSONANT;
            }
            else if(part.matches(DIGIT_REGEX)){
                type = TextElementType.DIGIT;
            }
            else{
                type = TextElementType.SYMBOL;
            }
            symbol.setType(type);
            element.add(symbol);
        }
    }
}
