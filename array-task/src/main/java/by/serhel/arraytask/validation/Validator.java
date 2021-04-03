package by.serhel.arraytask.validation;

public class Validator {
    private String REGEX = "(-?\\d+(,\\s)?)+";

    public boolean isValid(String line){
        boolean isValid = line.matches(REGEX);
        return isValid;
    }
}
