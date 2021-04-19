package by.serhel.xmlparsing.builder;

public enum CandyXMLTag {
    CANDIES("candies"),
    CHOCOLATE_CANDY("chocolate-candy"),
    CANDY("candy"),
    ENERGY("energy"),
    TYPE("type"),
    INGREDIENTS("ingredients"),
    INGREDIENT_ITEM("ingredient-item"),
    NAME("name"),
    WEIGHT_MG("weight-mg"),
    VALUE("value"),
    FATS("fats"),
    CARBOHYDRATES("carbohydrates"),
    PROTEINS("proteins"),
    PRODUCTION("production"),
    PRODUCTION_DATE("production-date"),
    CHOCOLATE_TYPE("chocolate-type");

    private final String value;

    CandyXMLTag(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
