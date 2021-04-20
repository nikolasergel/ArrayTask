package by.serhel.xmlparsing.builder;

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

public class CandySaxBuilder extends AbstractCandyBuilder {
    private static final Logger logger = LogManager.getLogger();
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

    public void build(String filePath) {
        try {
            reader.parse(filePath);
        } catch (IOException e) {
            var exception = new CustomParseXmlException(e);
            logger.error("Bad file path: " + filePath, exception);
        } catch (SAXException e) {
            var exception = new CustomParseXmlException(e);
            logger.error("XML parsing is failed!", exception);
        }
        this.candies = handler.getCandies();
        chocolateCandies = handler.getChocolateCandies();
    }
}