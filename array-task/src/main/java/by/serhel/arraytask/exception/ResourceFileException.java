package by.serhel.arraytask.exception;

public class ResourceFileException extends Exception{
    public ResourceFileException() {
    }

    public ResourceFileException(String message) {
        super(message);
    }

    public ResourceFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceFileException(Throwable cause) {
        super(cause);
    }

}
