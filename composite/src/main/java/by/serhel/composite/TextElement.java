package by.serhel.composite;

import java.util.ArrayList;
import java.util.List;

public class TextElement extends AbstractElement {
    private List<AbstractElement> elements;

    public TextElement() {
        this.elements = new ArrayList<>();
    }

    public TextElement(TextElementType type) {
        this.elements = new ArrayList<>();
        this.setType(type);
    }

    public boolean add(AbstractElement element) {
        return elements.add(element);
    }

    public boolean remove(AbstractElement element) {
        return elements.remove(element);
    }

    public List<AbstractElement> getChild() {
        return List.copyOf(elements);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (AbstractElement element : elements) {
            builder.append(element.toString());
        }
        return builder.toString();
    }
}
