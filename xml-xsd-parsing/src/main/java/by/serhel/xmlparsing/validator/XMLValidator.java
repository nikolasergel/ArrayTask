package by.serhel.xmlparsing.validator;

import by.serhel.xmlparsing.handler.CandyErrorHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XMLValidator {
    private static final Logger logger = LogManager.getLogger();

    public static boolean isValid(String fileName, String schemaName) {
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory factory = SchemaFactory.newInstance(language);
        File schemaLocation = new File(schemaName);
        boolean isValid = true;
        try {
            Schema schema = factory.newSchema(schemaLocation);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(fileName);
            validator.setErrorHandler(new CandyErrorHandler());
            validator.validate(source);
        } catch (SAXException | IOException e) {
            logger.error(fileName + " or \n" + schemaName + " is not correct or valid", e);
            isValid = false;
        }
        return isValid;
    }
}
