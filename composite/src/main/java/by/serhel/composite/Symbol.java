package by.serhel.composite;

public class Symbol implements Element {
    private String symbol;

    public Symbol() {
    }

    public Symbol(String symbol) {
        this.symbol = symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String getText() {
        return symbol;
    }
}
