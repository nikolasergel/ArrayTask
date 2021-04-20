package by.serhel.xmlparsing.validator;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class XMLValidatorTest {
    private String schemaName = "src/test/resources/data/schema.xsd";

    @Test
    public void testIsValid() {
        String filePath = "src/test/resources/data/valid.xml";
        boolean actual = XMLValidator.isValid(filePath, schemaName);
        assertTrue(actual);
    }

    @Test
    public void testIsInvalid() {
        String filePath = "src/test/resources/data/invalid.xml";
        boolean actual = XMLValidator.isValid(filePath, schemaName);
        assertFalse(actual);
    }
}