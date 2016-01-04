package github.therealbuggy.configurator.translator;

import github.therealbuggy.configurator.IConfigurator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jonathan on 02/01/16.
 */
public abstract class VariableTranslator<T> extends Translator<T> {
    VariableTranslator() {
        super();
    }
    VariableTranslator(IConfigurator configurator) {
        super(configurator);
    }

    public final Matcher variableMatcher(String expressionToMatch){
        Pattern pattern = Pattern.compile("(\\(\\$(.+)\\))");
        return pattern.matcher(expressionToMatch);
    }
}
