package by.serhel.arraytask.validator;

public class Validator {
    private String REGEX = "(-?\\d+(,\\s)?)+";

    public boolean isValid(String line){
        boolean isValid = line.matches(REGEX);
        return isValid;
    }
}
