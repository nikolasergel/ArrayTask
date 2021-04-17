package by.serhel.xmlparsing.builder;

import by.serhel.xmlparsing.entity.Candy;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class CandyHandler extends DefaultHandler {
    public static final String UNDERSCORE = "_";
    public static final String HYPHEN = "-";

    private List<Candy> candies;
    private Candy currentCandy;

    public CandyHandler() {
        this.candies = new ArrayList<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (attributes != null){
            attributes.
        }
        super.startElement(uri, localName, qName, attributes);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
    }

    public List<Candy> getCandies() {
        return candies;
    }
}
