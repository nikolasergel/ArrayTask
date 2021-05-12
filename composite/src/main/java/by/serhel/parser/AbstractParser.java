package by.serhel.parser;

import by.serhel.composite.Element;
import by.serhel.composite.TextElement;
import by.serhel.composite.TextElementType;

public abstract class AbstractParser {
    protected TextElement element;
    protected TextElementType type;
    protected AbstractParser next;

    public AbstractParser(AbstractParser next) {
        this.next = next;
        this.element = element;
    }

    public TextElement getElement() {
        return element;
    }

    public TextElementType getType() {
        return type;
    }

    public abstract TextElement parse(String text);
}
