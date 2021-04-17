package by.serhel.xmlparsing.builder;

import by.serhel.xmlparsing.entity.Candy;
//import by.epam.learn.xml.handler.StudentErrorHandler;
//import by.epam.learn.xml.handler.StudentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.Set;

public class CandySaxBuilder {
    private Set<Candy> students;
    private CandyHandler handler = new CandyHandler();
    private XMLReader reader;

    public CandySaxBuilder() {
// reader configuration
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();
            reader = saxParser.getXMLReader();
        } catch (ParserConfigurationException | SAXException e) {
            e.printStackTrace(); // log
        }
        reader.setErrorHandler(new CandyErrorHandler());
        reader.setContentHandler(handler);
    }

    public Set<Candy> getStudents() {
        return students;
    }

    public void buildSetStudents(String filename) {
        try {
            reader.parse(filename);
        } catch (IOException | SAXException e) {} catch (IOException | SAXException e) {
            e.printStackTrace(); // log
        }
        students = handler.getCandies();
    }
}