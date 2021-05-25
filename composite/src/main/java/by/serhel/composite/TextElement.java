package by.serhel.composite;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class TextElement extends AbstractElement {
    private List<AbstractElement> elements = new ArrayList<>();

    public TextElement() {
    }

    public TextElement(TextElementType type) {
        this.setType(type);
    }

    public TextElement(String text, TextElementType type) {
        this.setValue(text);
        this.setType(type);
    }

    public boolean add(AbstractElement element) {
        return elements.add(element);
    }

    @Override
    public boolean addAll(Collection<? extends AbstractElement> collection) {
        return elements.addAll(collection);
    }

    public boolean remove(AbstractElement element) {
        return elements.remove(element);
    }

    @Override
    public boolean removeAll(Collection<? extends AbstractElement> collection) {
        return elements.removeAll(collection);
    }

    public List<AbstractElement> getChild() {
        return List.copyOf(elements);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (!elements.isEmpty()) {
            for (AbstractElement element : elements) {
                builder.append(element.toString());
            }
        } else {
            return this.getValue();
        }
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass() || !super.equals(o)) {
            return false;
        }
        TextElement element = (TextElement) o;
        return elements.equals(element.elements);
    }

    @Override
    public int hashCode() {
        int c = 83;
        int result = c;
        result *= super.hashCode() * c;
        result *= Objects.hash(elements) * c;
        return result;
    }
}
