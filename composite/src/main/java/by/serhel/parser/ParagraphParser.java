package by.serhel.parser;

import by.serhel.composite.SymbolElement;
import by.serhel.composite.TextElement;
import by.serhel.composite.TextElementType;

public class ParagraphParser extends AbstractParser {
    public static String PARAGRAPH_REGEX = "(?=(\n))|(?<=(\n))";

    public ParagraphParser(SentenceParser next) {
        super(next);
    }

    @Override
    public void parse(String text, TextElement element) {
        String[] parts = text.split(PARAGRAPH_REGEX);

        for (String part : parts) {
            TextElement paragraph = new TextElement(TextElementType.PARAGRAPH);
            next.parse(part, paragraph);
            element.add(paragraph);
        }
    }
}
