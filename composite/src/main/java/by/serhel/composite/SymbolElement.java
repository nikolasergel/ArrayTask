package by.serhel.composite;

import java.util.List;

public class SymbolElement extends AbstractElement {
    private String symbol;

    public SymbolElement() {
    }

    public SymbolElement(String symbol) {
        this.symbol = symbol;
    }

    public SymbolElement(String symbol, TextElementType type) {
        this.symbol = symbol;
        this.setType(type);
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public boolean add(AbstractElement element) {
        throw new UnsupportedOperationException("Method 'add' is unsupported in class Symbol");
    }

    @Override
    public boolean remove(AbstractElement element) {
        throw new UnsupportedOperationException("Method 'remove' is unsupported in class Symbol");
    }

    @Override
    public List<AbstractElement> getChild() {
        throw new UnsupportedOperationException("Method 'getChild' is unsupported in class Symbol");
    }

    @Override
    public String toString() {
        return symbol;
    }
}
