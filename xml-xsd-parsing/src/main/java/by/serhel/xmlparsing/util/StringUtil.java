package by.serhel.xmlparsing.util;

public class StringUtil {
    public static final String UNDERSCORE = "_";
    public static final String HYPHEN = "-";

    public static String toConstantCase(String tag){
        tag = tag.toUpperCase().replaceAll(HYPHEN, UNDERSCORE);
        return tag;
    }
}
