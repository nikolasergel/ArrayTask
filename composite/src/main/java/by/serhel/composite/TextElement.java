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

    public boolean addElement(Element element){
        return elements.add(element);
    }

    @Override
    public String getText() {
        StringBuilder builder = new StringBuilder();
        for(Element element : elements){
            builder.append(element.getText());
        }
        return builder.toString();
    }
}
