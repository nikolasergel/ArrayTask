package by.serhel.xmlparsing;

import by.serhel.xmlparsing.builder.AbstactCandyBuilder;
import by.serhel.xmlparsing.builder.CandySaxBuilder;
import by.serhel.xmlparsing.builder.CandyStaxBuilder;
import by.serhel.xmlparsing.entity.Candy;
import by.serhel.xmlparsing.entity.ChocolateCandy;
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
//              AbstractCandyBuilder builder = new CandySaxBuilder();
                AbstactCandyBuilder builder = new CandyStaxBuilder();
                builder.build("src/main/resources/data/candies.xml");
                if (builder.getChocolateCandies() != null) {
                    for (ChocolateCandy candy : builder.getChocolateCandies()) {
                        System.out.println(candy);
                    }
                }
                if (builder.getCandies() != null) {
                    for (Candy candy : builder.getCandies()) {
                        System.out.println(candy);
                    }
                }
            } catch (CustomParseXmlException e) {
                e.printStackTrace();
            }
        }
    }
}

