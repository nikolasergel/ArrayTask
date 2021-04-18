package by.serhel.xmlparsing.handler;

import by.serhel.xmlparsing.builder.CandyXMLTag;
import by.serhel.xmlparsing.entity.Candy;
import by.serhel.xmlparsing.entity.ChocolateCandy;
import by.serhel.xmlparsing.entity.Ingredient;
import by.serhel.xmlparsing.entity.Value;
import by.serhel.xmlparsing.entity.type.CandyType;
import by.serhel.xmlparsing.entity.type.ChocolateType;
import by.serhel.xmlparsing.entity.type.Origin;
import by.serhel.xmlparsing.util.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDate;
import java.util.*;

public class CandyHandler extends DefaultHandler {
    private static final Logger logger = LogManager.getLogger();
    private Set<Candy> candies;
    private Set<ChocolateCandy> chocolateCandies;
    private Candy currentCandy;
    private List<Ingredient> ingredientList;
    private Ingredient currentIngredient;
    private Value currentValue;
    private CandyXMLTag currentXmlTag;
    private EnumSet<CandyXMLTag> fields;

    public CandyHandler() {
        this.candies = new HashSet<>();
        this.chocolateCandies = new HashSet<>();
        this.fields = EnumSet.range(CandyXMLTag.ENERGY, CandyXMLTag.CHOCOLATE_TYPE);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        String constantName = StringUtil.toConstantCase(qName);
        CandyXMLTag tag = CandyXMLTag.valueOf(constantName);
        if(CandyXMLTag.CANDY == tag || CandyXMLTag.CHOCOLATE_CANDY == tag){
            currentCandy = CandyXMLTag.CANDY == tag ? new Candy() : new ChocolateCandy();
            currentCandy.setName(attributes.getValue(0));
            if(attributes.getLength() == 2){
                String origin = attributes.getValue(1);
                currentCandy.setOrigin(Origin.valueOf(origin));
            }
        }
        else if(CandyXMLTag.INGREDIENTS == tag){
            ingredientList = new ArrayList<>();
        }
        else if(CandyXMLTag.INGREDIENT_ITEM == tag){
            currentIngredient = new Ingredient();
        }
        else if(CandyXMLTag.VALUE == tag){
            currentValue = new Value();
        }
        else {
            if(fields.contains(tag)){
                currentXmlTag = tag;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        String constantName = StringUtil.toConstantCase(qName);
        CandyXMLTag tag = CandyXMLTag.valueOf(constantName);
        switch (tag){
            case CANDY -> candies.add(currentCandy);
            case CHOCOLATE_CANDY -> chocolateCandies.add((ChocolateCandy) currentCandy);
            case INGREDIENTS -> currentCandy.setIngredients(ingredientList);
            case INGREDIENT_ITEM -> ingredientList.add(currentIngredient);
            case VALUE -> currentCandy.setValue(currentValue);
            default -> {}
        }
        currentXmlTag = null;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String text = new String(ch, start, length);
        if(currentXmlTag != null){
            switch (currentXmlTag){
                case ENERGY -> currentCandy.setEnergy(Integer.parseInt(text));
                case TYPE -> currentCandy.setType(CandyType.valueOf(text));
                case WEIGHT_MG -> currentIngredient.setWeightMG(Integer.parseInt(text));
                case NAME -> currentIngredient.setName(text);
                case FATS -> currentValue.setFats(Double.parseDouble(text));
                case CARBOHYDRATES -> currentValue.setCarbohydrates(Double.parseDouble(text));
                case PROTEINS -> currentValue.setProteins(Double.parseDouble(text));
                case PRODUCTION -> currentCandy.setProduction(text);
                case PRODUCTION_DATE -> currentCandy.setProductionDate(LocalDate.parse(text));
                case CHOCOLATE_TYPE -> {
                    ChocolateType type = ChocolateType.valueOf(text);
                    ChocolateCandy candy = (ChocolateCandy)currentCandy;
                    candy.setChocolateType(type);
                }
                default -> {
                    logger.error("Xml tag '" + text + "'don't have processing!");
                }
            }
        }
    }

    public Set<Candy> getCandies() {
        return candies;
    }

    public Set<ChocolateCandy> getChocolateCandies() {
        return chocolateCandies;
    }
}
