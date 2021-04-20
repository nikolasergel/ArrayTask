package by.serhel.xmlparsing.builder;

import by.serhel.xmlparsing.exception.CustomParseXmlException;
import by.serhel.xmlparsing.handler.CandyErrorHandler;
import by.serhel.xmlparsing.handler.CandyHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class CandySaxBuilder extends AbstractCandyBuilder {
    private CandyHandler handler = new CandyHandler();
    private XMLReader reader;

    public CandySaxBuilder() throws CustomParseXmlException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();
            reader = saxParser.getXMLReader();
        } catch (ParserConfigurationException e) {
            throw new CustomParseXmlException("Can't configuring SAXParser!", e);
        } catch (SAXException e){
            throw new CustomParseXmlException("Can't create SAXParser or XMLReader!", e);
        }
        reader.setErrorHandler(new CandyErrorHandler());
        reader.setContentHandler(handler);
    }

    public void build(String filePath) throws CustomParseXmlException {
        try {
            reader.parse(filePath);
        } catch (IOException e) {
            throw new CustomParseXmlException("Bad file path: " + filePath, e);
        } catch (SAXException e) {
            throw new CustomParseXmlException("XML parsing is failed!", e);
        }
        this.candies = handler.getCandies();
        this.chocolateCandies = handler.getChocolateCandies();
    }
}