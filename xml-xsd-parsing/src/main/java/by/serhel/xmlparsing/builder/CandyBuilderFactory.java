package by.serhel.xmlparsing.builder;

import by.serhel.xmlparsing.exception.CustomParseXmlException;

public class CandyBuilderFactory {
    private enum TypeParser {
        SAX, STAX, DOM
    }
    private CandyBuilderFactory() {
    }

    public static AbstractCandyBuilder createCandyBuilder(String typeParser) throws CustomParseXmlException {
        TypeParser type = TypeParser.valueOf(typeParser.toUpperCase());
        switch (type) {
            case DOM -> {
                return new CandyDomBuilder();
            }
            case STAX -> {
                return new CandyStaxBuilder();
            }
            case SAX -> {
                return new CandySaxBuilder();
            }
            default -> throw new EnumConstantNotPresentException(
                    type.getDeclaringClass(), type.name());
        }
    }
}
