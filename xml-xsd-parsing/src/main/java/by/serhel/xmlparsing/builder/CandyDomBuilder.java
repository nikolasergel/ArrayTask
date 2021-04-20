package by.serhel.xmlparsing.builder;

import by.serhel.xmlparsing.entity.Candy;
import by.serhel.xmlparsing.entity.ChocolateCandy;
import by.serhel.xmlparsing.entity.Ingredient;
import by.serhel.xmlparsing.entity.Value;
import by.serhel.xmlparsing.entity.type.CandyType;
import by.serhel.xmlparsing.entity.type.ChocolateType;
import by.serhel.xmlparsing.exception.CustomParseXmlException;
import by.serhel.xmlparsing.exception.ResourceNotFoundException;
import by.serhel.xmlparsing.util.StringUtil;
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
    private DocumentBuilder builder;

    public CandyDomBuilder() throws CustomParseXmlException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            this.builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new CustomParseXmlException("XML parsing is failed!", e);
        }
    }

    @Override
    public void build(String filePath) throws CustomParseXmlException, ResourceNotFoundException {
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
            throw new ResourceNotFoundException("Bad file path: " + filePath, e);
        } catch (SAXException e) {
            throw new CustomParseXmlException("XML file is incorrect.", e);
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
                for(int j = 0; j < ingredientFields.getLength(); j++){
                    Node item = ingredientFields.item(j);
                    if(item.getNodeType() == Node.ELEMENT_NODE) {
                        String nodeName = item.getNodeName();
                        if(nodeName.equals(CandyXMLTag.NAME.getValue())){
                            ingredient.setName(item.getTextContent());
                        }
                        else if(nodeName.equals(CandyXMLTag.WEIGHT_MG.getValue())){
                            ingredient.setWeightMG(Integer.parseInt(item.getTextContent()));
                            ingredientList.add(ingredient);
                        }
                    }
                }
            }
        }
        return ingredientList;
    }

    private Value buildValue(NodeList nodes){
        Value value = new Value();
        for(int i = 0; i < nodes.getLength(); i++){
            Node node = nodes.item(i);
            if(node.getNodeType() == Node.ELEMENT_NODE) {
                String nodeName = node.getNodeName();
                if (nodeName.equals(CandyXMLTag.FATS.getValue())) {
                    value.setFats(Double.parseDouble(node.getTextContent()));
                }
                else if(nodeName.equals(CandyXMLTag.CARBOHYDRATES.getValue())){
                    value.setCarbohydrates(Double.parseDouble(node.getTextContent()));
                }
                else if(nodeName.equals(CandyXMLTag.PROTEINS.getValue())){
                    value.setProteins(Double.parseDouble(node.getTextContent()));
                }
            }
        }
        return value;
    }
}
