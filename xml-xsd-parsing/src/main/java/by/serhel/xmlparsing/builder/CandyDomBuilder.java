package by.serhel.xmlparsing.builder;

import by.serhel.xmlparsing.entity.Candy;
import by.serhel.xmlparsing.entity.ChocolateCandy;
import by.serhel.xmlparsing.entity.Ingredient;
import by.serhel.xmlparsing.entity.Value;
import by.serhel.xmlparsing.entity.type.CandyType;
import by.serhel.xmlparsing.entity.type.ChocolateType;
import by.serhel.xmlparsing.exception.CustomParseXmlException;
import by.serhel.xmlparsing.util.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CandyDomBuilder extends AbstractCandyBuilder{
    private static final Logger logger = LogManager.getLogger();
    private DocumentBuilder builder;

    public CandyDomBuilder() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            this.builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            var exception = new CustomParseXmlException(e);
            logger.error("XML parsing is failed!", exception);
        }
    }

    @Override
    public void build(String filePath) throws CustomParseXmlException {
        try{
            Document document = builder.parse(filePath);
            Element root = document.getDocumentElement();
            NodeList candyList = root.getElementsByTagName(CandyXMLTag.CANDY.getValue());
            NodeList chocolateCandyList = root.getElementsByTagName(CandyXMLTag.CHOCOLATE_CANDY.getValue());
            for (int i = 0; i < candyList.getLength(); i++){
                Node node = candyList.item(i);
                Candy candy = buildCandy(node, new Candy());
                candies.add(candy);
            }
            for (int i = 0; i < chocolateCandyList.getLength(); i++){
                Node node = chocolateCandyList.item(i);
                ChocolateCandy candy = (ChocolateCandy) buildCandy(node, new ChocolateCandy());
                chocolateCandies.add(candy);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    private Candy buildCandy(Node node, Candy candy){
        NamedNodeMap attributes = node.getAttributes();
        candy.setName(attributes.getNamedItem("name").getTextContent());
        if(attributes.getLength() == 2){
            candy.setName(attributes.getNamedItem("origin").getTextContent());
        }
        NodeList nodeList = node.getChildNodes();
        for(int i = 0; i < nodeList.getLength(); i++){
            Node item = nodeList.item(i);
            if(item.getNodeType() == Node.ELEMENT_NODE) {
                CandyXMLTag tag = CandyXMLTag.valueOf(StringUtil.toConstantCase(item.getNodeName()));
                switch (tag) {
                    case ENERGY -> candy.setEnergy(Integer.parseInt(item.getTextContent()));
                    case TYPE -> candy.setType(CandyType.valueOf(item.getTextContent()));
                    case INGREDIENTS -> candy.setIngredients(buildIngredients(item.getChildNodes()));
                    case VALUE -> candy.setValue(buildValue(item.getChildNodes()));
                    case PRODUCTION -> candy.setProduction(item.getTextContent());
                    case PRODUCTION_DATE -> candy.setProductionDate(LocalDate.parse(item.getTextContent()));
                    case CHOCOLATE_TYPE -> {
                        ChocolateCandy chocolateCandy = (ChocolateCandy) candy;
                        chocolateCandy.setChocolateType(ChocolateType.valueOf(item.getTextContent()));
                    }
                }
            }
        }
        return candy;
    }

    private List<Ingredient> buildIngredients(NodeList nodes){
        Node node;
        Ingredient ingredient;
        List<Ingredient> ingredientList = new ArrayList<>();
        for(int i = 0; i < nodes.getLength(); i++){
            node = nodes.item(i);
            if(node.getNodeType() == Node.ELEMENT_NODE) {
                NodeList ingredientFields = node.getChildNodes();
                ingredient = new Ingredient();
                ingredient.setName(ingredientFields.item(1).getTextContent());
                ingredient.setWeightMG(Integer.parseInt(ingredientFields.item(3).getTextContent()));
                ingredientList.add(ingredient);
            }
        }
        return ingredientList;
    }

    private Value buildValue(NodeList nodes){
        Value value = new Value();
        Node node = nodes.item(1);
        value.setFats(Double.parseDouble(node.getTextContent()));
        node = nodes.item(3);
        value.setCarbohydrates(Double.parseDouble(node.getTextContent()));
        node = nodes.item(5);
        value.setProteins(Double.parseDouble(node.getTextContent()));
        return value;
    }
}
