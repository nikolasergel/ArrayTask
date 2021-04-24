package by.serhel.shapestask.parser;

import by.serhel.shapestask.exception.ShapeException;
import by.serhel.shapestask.validator.ConeStringValidator;

import java.util.Arrays;

public class ConeDataParser {
    public static final String SEPARATOR_PATTERN = "\\s+";

    public double[] parseConeString(String coneData) throws ShapeException {
        if(!ConeStringValidator.isValid(coneData)){
            throw new ShapeException("The input data isn't valid: " + coneData);
        }
        String[] strData = coneData.strip().split(SEPARATOR_PATTERN);
        double[] numbers = Arrays.stream(strData)
                .mapToDouble(Double::parseDouble)
                .toArray();
        return numbers;
    }
}
