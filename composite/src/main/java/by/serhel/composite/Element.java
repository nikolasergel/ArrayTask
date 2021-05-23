package by.serhel.composite;

public abstract class Element {
    private TextElementType type;

    public TextElementType getType() {
        return type;
    }

    public void setType(TextElementType type) {
        this.type = type;
    }
}
