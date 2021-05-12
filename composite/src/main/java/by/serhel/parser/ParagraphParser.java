package by.serhel.parser;

import by.serhel.composite.Symbol;
import by.serhel.composite.TextElement;
import by.serhel.composite.TextElementType;

public class ParagraphParser extends AbstractParser{
    public static String PARAGRAPH_REGEX = "\n\t";

    public ParagraphParser(SentenceParser next) {
        super(next);
    }

    @Override
    public TextElement parse(String text) {
        String[] paragraphs = text.split(PARAGRAPH_REGEX);
        element = new TextElement();
        element.setType(TextElementType.PARAGRAPH);
        for(String paragraph : paragraphs){
            element.addElement(next.parse(paragraph));
            element.addElement(new Symbol(PARAGRAPH_REGEX, TextElementType.SYMBOL));
        }
        return element;
    }
}
