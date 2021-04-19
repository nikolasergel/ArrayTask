package by.serhel.xmlparsing.builder;

import by.serhel.xmlparsing.entity.Candy;
import by.serhel.xmlparsing.entity.ChocolateCandy;
import by.serhel.xmlparsing.entity.Ingredient;
import by.serhel.xmlparsing.entity.Value;
import by.serhel.xmlparsing.entity.type.CandyType;
import by.serhel.xmlparsing.entity.type.ChocolateType;
import by.serhel.xmlparsing.entity.type.Origin;
import by.serhel.xmlparsing.exception.CustomParseXmlException;
import by.serhel.xmlparsing.util.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CandyStaxBuilder extends AbstactCandyBuilder{
    private static final Logger logger = LogManager.getLogger();
    private XMLInputFactory factory;

    public CandyStaxBuilder() {
        super();
        this.factory = XMLInputFactory.newInstance();
    }

    @Override
    public void build(String filePath) throws CustomParseXmlException {
        XMLStreamReader reader;
        Candy candy = null;
        try(InputStream stream = Files.newInputStream(Paths.get(filePath))){
            reader = factory.createXMLStreamReader(stream);
            while(reader.hasNext()){
                reader.next();
                if(reader.isStartElement()){
                    String tagName = reader.getLocalName();
                    if(tagName.equals(CandyXMLTag.CANDY.getValue())){
                        candy = new Candy();
                        buildCandy(reader, candy);
                        candies.add(candy);
                    }
                    else if(tagName.equals(CandyXMLTag.CHOCOLATE_CANDY.getValue())){
                        candy = new ChocolateCandy();
                        buildChocolateCandy(reader, (ChocolateCandy) candy);
                        chocolateCandies.add((ChocolateCandy) candy);
                    }
                }
            }
        } catch (IOException e) {
            var exception = new CustomParseXmlException(e);
            logger.error("Bad file path: " + filePath, exception);
        } catch (XMLStreamException e) {
            var exception = new CustomParseXmlException(e);
            logger.error("XML parsing is failed!", exception);
        }
    }

    private Candy buildCandy(XMLStreamReader reader, Candy candy) throws XMLStreamException {
        candy.setName(reader.getAttributeValue(0));
        if(reader.getAttributeCount() == 2){
            candy.setOrigin(Origin.valueOf(reader.getAttributeValue(1)));
        }
        while (reader.hasNext()){
            reader.next();
            if((reader.isStartElement() || reader.isEndElement())
                    && (reader.getLocalName().equals(CandyXMLTag.CANDY.getValue())
                    || reader.getLocalName().equals(CandyXMLTag.CHOCOLATE_TYPE.getValue()))){
                break;
            }
            if(reader.isStartElement()){
                CandyXMLTag tag = CandyXMLTag.valueOf(StringUtil.toConstantCase(reader.getLocalName()));
                switch (tag) {
                    case ENERGY -> candy.setEnergy(Integer.parseInt(getText(reader)));
                    case TYPE -> candy.setType(CandyType.valueOf(getText(reader)));
                    case INGREDIENTS -> candy.setIngredients(buildIngredients(reader));
                    case VALUE -> candy.setValue(buildValue(reader));
                    case PRODUCTION -> candy.setProduction(getText(reader));
                    case PRODUCTION_DATE -> candy.setProductionDate(LocalDate.parse(getText(reader)));
                    default -> logger.error("Xml tag '" + tag.getValue() + "'doesn't have processing!");
                }
            }
        }
        return candy;
    }

    private ChocolateCandy buildChocolateCandy(XMLStreamReader reader, ChocolateCandy candy) throws XMLStreamException {
        buildCandy(reader, candy);
        candy.setChocolateType(ChocolateType.valueOf(getText(reader)));
        return candy;
    }

    private Value buildValue(XMLStreamReader reader) throws XMLStreamException {
        Value value = new Value();
        while(reader.hasNext()){
            reader.next();
            if(reader.isStartElement()){
                String name = StringUtil.toConstantCase(reader.getLocalName());
                switch (CandyXMLTag.valueOf(name)){
                    case FATS -> value.setFats(Double.parseDouble(getText(reader)));
                    case PROTEINS -> value.setProteins(Double.parseDouble(getText(reader)));
                    case CARBOHYDRATES -> value.setCarbohydrates(Double.parseDouble(getText(reader)));
                }
            }
            if(reader.isEndElement() && reader.getLocalName().equals(CandyXMLTag.VALUE.getValue())){
                break;
            }
        }
        return value;
    }

    private List<Ingredient> buildIngredients(XMLStreamReader reader) throws XMLStreamException {
        List<Ingredient> ingredientList = new ArrayList<>();
        Ingredient ingredient = new Ingredient();
        while(reader.hasNext()){
            reader.next();
            if(reader.isStartElement()){
                String tagName = StringUtil.toConstantCase(reader.getLocalName());
                CandyXMLTag tag = CandyXMLTag.valueOf(tagName);
                switch (tag){
                    case NAME -> ingredient.setName(getText(reader));
                    case WEIGHT_MG -> {
                        ingredient.setWeightMG(Integer.parseInt(getText(reader)));
                        ingredientList.add(ingredient);
                        ingredient = new Ingredient();
                    }
                }
            }
            if(reader.isEndElement() && reader.getLocalName().equals(CandyXMLTag.INGREDIENTS.getValue())){
                break;
            }
        }
        return ingredientList;
    }

    private String getText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if(reader.hasNext()){
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}
