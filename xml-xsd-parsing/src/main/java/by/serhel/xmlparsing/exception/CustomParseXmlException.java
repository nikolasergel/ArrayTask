package by.serhel.xmlparsing.exception;

public class CustomParseXmlException extends Exception{
    public CustomParseXmlException() {
    }

    public CustomParseXmlException(String message) {
        super(message);
    }

    public CustomParseXmlException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomParseXmlException(Throwable cause) {
        super(cause);
    }
}
