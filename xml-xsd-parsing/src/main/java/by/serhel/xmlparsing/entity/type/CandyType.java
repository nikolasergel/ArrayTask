package by.serhel.xmlparsing.entity.type;

public enum CandyType {
    CARAMEL("Caramel"),
    CHOCOLATE_WITH_FILLING("Chocolate with filling"),
    CHOCOLATE("Chocolate"),
    GELATIN("Gelatin");

    private final String type;

    CandyType(String type) {
        this.type = type;
    }
}
