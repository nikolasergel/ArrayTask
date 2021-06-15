package by.sergel.exception;

public class ProcessingShipException extends Exception{
    public ProcessingShipException(){
    }

    public ProcessingShipException(String message) {
        super(message);
    }

    public ProcessingShipException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProcessingShipException(Throwable cause) {
        super(cause);
    }
}
