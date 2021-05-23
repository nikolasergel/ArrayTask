package by.serhel.composite;

import java.util.List;

public class Symbol extends Element {
    private String symbol;

    public Symbol() {
    }

    public Symbol(String symbol) {
        this.symbol = symbol;
    }

    public Symbol(String symbol, TextElementType type) {
        this.symbol = symbol;
        this.setType(type);
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public boolean add(Element element) {
        throw new UnsupportedOperationException("Method 'add' is unsupported in class Symbol");
    }

    @Override
    public boolean remove(Element element) {
        throw new UnsupportedOperationException("Method 'remove' is unsupported in class Symbol");
    }

    @Override
    public List<Element> getChild() {
        throw new UnsupportedOperationException("Method 'getChild' is unsupported in class Symbol");
    }

    @Override
    public String toString() {
        return symbol;
    }
}
