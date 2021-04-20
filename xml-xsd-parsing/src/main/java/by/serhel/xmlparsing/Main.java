package by.serhel.xmlparsing;

import by.serhel.xmlparsing.builder.AbstractCandyBuilder;
import by.serhel.xmlparsing.builder.CandyBuilderFactory;
import by.serhel.xmlparsing.exception.CustomParseXmlException;
import by.serhel.xmlparsing.exception.ResourceNotFoundException;
import by.serhel.xmlparsing.util.ResourceUtil;
import by.serhel.xmlparsing.validator.XMLValidator;

public class Main {
    public static void main(String[] args) throws ResourceNotFoundException {
        ResourceUtil util = new ResourceUtil();
        String filePath = util.getPathToResources("data/candies.xml");
        String schemaPath = util.getPathToResources("data/schema.xsd");

        if (XMLValidator.isValid(filePath, schemaPath)) {
            try {
                AbstractCandyBuilder builder = CandyBuilderFactory.createCandyBuilder("DOM");
                builder.build(filePath);
                builder = CandyBuilderFactory.createCandyBuilder("SAX");
                builder.build(filePath);
                builder = CandyBuilderFactory.createCandyBuilder("STAX");
                builder.build(filePath);
            } catch (CustomParseXmlException | ResourceNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}

