package by.serhel.composite;

import java.util.ArrayList;
import java.util.List;

public class TextElement extends Element {
    private List<Element> elements;

    public TextElement() {
        this.elements = new ArrayList<>();
    }

    public TextElement(TextElementType type) {
        this.elements = new ArrayList<>();
        this.setType(type);
    }

    public boolean add(Element element) {
        return elements.add(element);
    }

    public boolean remove(Element element) {
        return elements.remove(element);
    }

    public List<Element> getChild() {
        return List.copyOf(elements);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Element element : elements) {
            builder.append(element.toString());
        }
        return builder.toString();
    }
}
