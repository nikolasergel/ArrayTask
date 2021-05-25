package by.serhel.composite;

import java.util.Collection;
import java.util.List;

public abstract class AbstractElement {
    private String value = "";
    private TextElementType type = TextElementType.TEXT;

    public TextElementType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public void setType(TextElementType type) {
        this.type = type;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public abstract boolean add(AbstractElement element);

    public abstract boolean addAll(Collection<? extends AbstractElement> collection);

    public abstract boolean remove(AbstractElement element);

    public abstract boolean removeAll(Collection<? extends AbstractElement> collection);

    public abstract List<AbstractElement> getChild();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbstractElement element = (AbstractElement) o;
        return (value == null) ? (element.value == null) : value.equals(element.value) &&
                type == element.type;
    }

    @Override
    public int hashCode() {
        int c = 31;
        int result = c;
        if (type != null) {
            result *= type.ordinal() * c;
        }
        if (value != null) {
            result *= value.hashCode() * c;
        }
        return result;
    }
}
