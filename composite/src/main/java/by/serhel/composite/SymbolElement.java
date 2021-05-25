package by.serhel.composite;

import java.util.Collection;
import java.util.List;

public class SymbolElement extends AbstractElement {

    public SymbolElement() {
    }

    public SymbolElement(String symbol) {
        this.setValue(symbol);
    }

    public SymbolElement(String symbol, TextElementType type) {
        this.setValue(symbol);
        this.setType(type);
    }

    @Override
    public boolean add(AbstractElement element) {
        throw new UnsupportedOperationException("Method 'add' is unsupported in class Symbol");
    }

    @Override
    public boolean addAll(Collection<? extends AbstractElement> collection) {
        throw new UnsupportedOperationException("Method 'addAll' is unsupported in class Symbol");
    }

    @Override
    public boolean remove(AbstractElement element) {
        throw new UnsupportedOperationException("Method 'remove' is unsupported in class Symbol");
    }

    @Override
    public boolean removeAll(Collection<? extends AbstractElement> collection) {
        throw new UnsupportedOperationException("Method 'removeAll' is unsupported in class Symbol");
    }

    @Override
    public List<AbstractElement> getChild() {
        throw new UnsupportedOperationException("Method 'getChild' is unsupported in class Symbol");
    }

    @Override
    public String toString() {
        return this.getValue();
    }
}
