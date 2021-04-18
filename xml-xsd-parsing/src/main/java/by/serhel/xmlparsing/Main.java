package by.serhel.xmlparsing;

import by.serhel.xmlparsing.builder.CandySaxBuilder;
import by.serhel.xmlparsing.entity.Candy;
import by.serhel.xmlparsing.entity.ChocolateCandy;
import by.serhel.xmlparsing.exception.CustomParseXmlException;

public class Main {
    public static void main(String[] args) {
        try {
            CandySaxBuilder builder = new CandySaxBuilder();
            builder.buildSetStudents("src/main/resources/data/candies.xml");
            if(builder.getChocolateCandies() != null){
                for(ChocolateCandy candy : builder.getChocolateCandies()){
                    System.out.println(candy);
                }
            }
            if(builder.getCandies() != null){
                for(Candy candy : builder.getCandies()){
                    System.out.println(candy);
                }
            }
        } catch (CustomParseXmlException e) {
            e.printStackTrace();
        }
    }
}

