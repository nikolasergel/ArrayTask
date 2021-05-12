package by.serhel.composite;

import java.util.ArrayList;
import java.util.List;

public class TextElement implements Element {
    private List<Element> elements;

    public TextElement() {
        this.elements = new ArrayList<>();
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
