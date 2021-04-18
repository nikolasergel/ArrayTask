package by.serhel.xmlparsing.builder;

import by.serhel.xmlparsing.entity.Candy;
import by.serhel.xmlparsing.entity.ChocolateCandy;
import by.serhel.xmlparsing.exception.CustomParseXmlException;
import by.serhel.xmlparsing.handler.CandyErrorHandler;
import by.serhel.xmlparsing.handler.CandyHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.Set;

public class CandySaxBuilder {
    private static final Logger logger = LogManager.getLogger();
    private Set<Candy> candies;
    private Set<ChocolateCandy> chocolateCandies;
    private CandyHandler handler = new CandyHandler();
    private XMLReader reader;

    public CandySaxBuilder() throws CustomParseXmlException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();
            reader = saxParser.getXMLReader();
        } catch (ParserConfigurationException e) {
            var exception = new CustomParseXmlException(e);
            logger.error("Can't configuring SAXParser!", exception);
            throw exception;
        } catch (SAXException e){
            var exception = new CustomParseXmlException(e);
            logger.error("Can't create SAXParser or XMLReader!", exception);
            throw exception;
        }
        reader.setErrorHandler(new CandyErrorHandler());
        reader.setContentHandler(handler);
    }

    public Set<Candy> getCandies() {
        return candies;
    }

    public Set<ChocolateCandy> getChocolateCandies() {
        return chocolateCandies;
    }

    public void buildSetStudents(String filepath) {
        try {
            reader.parse(filepath);
        } catch (IOException e) {
            var exception = new CustomParseXmlException(e);
            logger.error("Bad filepath: " + filepath, exception);
        } catch (SAXException e) {
            var exception = new CustomParseXmlException(e);
            logger.error("XML parse is failed!", exception);
        }
        candies = handler.getCandies();
        chocolateCandies = handler.getChocolateCandies();
    }
}