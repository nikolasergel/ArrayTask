package by.serhel.composite;

import java.util.List;

public abstract class AbstractElement {
    private TextElementType type = TextElementType.TEXT;

    public TextElementType getType() {
        return type;
    }

    public abstract boolean add(AbstractElement element);

    public abstract boolean remove(AbstractElement element);

    public abstract List<AbstractElement> getChild();

    public void setType(TextElementType type) {
        this.type = type;
    }
}
