package by.serhel.shapestask.validator;

public class ConeStringValidator {
    public static final String CONE_STRING_PATTERN = "(-?\\d+(\\.\\d+)?\\s?){6}\\d(\\.\\d+)?";

    public static boolean isValid(String data){
        if(data == null || data.isBlank()){
            return false;
        }
        return data.matches(CONE_STRING_PATTERN);
    }
}
