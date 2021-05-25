package by.serhel.exception;

public class NotExpectedElementException extends Exception{
    public NotExpectedElementException() {
    }

    public NotExpectedElementException(String message) {
        super(message);
    }

    public NotExpectedElementException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotExpectedElementException(Throwable cause) {
        super(cause);
    }
}
