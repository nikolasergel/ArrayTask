package by.serhel.composite;

import java.util.List;

public abstract class Element {
    private TextElementType type;

    public TextElementType getType() {
        return type;
    }

    public abstract boolean add(Element element);

    public abstract boolean remove(Element element);

    public abstract List<Element> getChild();

    public void setType(TextElementType type) {
        this.type = type;
    }
}
