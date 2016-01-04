package github.therealbuggy.configurator.utils;

import java.util.regex.Pattern;

/**
  * Created by jonathan on 20/12/15.
  */
public class BooleanUtil {

    private static final Pattern truePattern = Pattern.compile("(?i)(s|y|t|sim|yes|true)");
    private static final Pattern falsePattern = Pattern.compile("(?i)(n|f|nao|no|false)");

    public static boolean booleanFromString(String stringRepresentation) {
        if(truePattern.matcher(stringRepresentation).matches())return true;
        if(falsePattern.matcher(stringRepresentation).matches())return false;
        throw new RuntimeException("Cannot get value from string representation: "+stringRepresentation);
    }
}
