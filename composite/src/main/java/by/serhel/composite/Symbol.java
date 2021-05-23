package by.serhel.composite;

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
    public String toString() {
        return symbol;
    }
}
