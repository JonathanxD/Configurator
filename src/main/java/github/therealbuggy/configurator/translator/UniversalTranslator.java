package github.therealbuggy.configurator.translator;

import github.therealbuggy.configurator.IConfigurator;

import java.util.regex.Matcher;

/**
 * Created by jonathan on 02/01/16.
 */
public class UniversalTranslator extends VariableTranslator<String> {

    UniversalTranslator() {

    }

    public UniversalTranslator(IConfigurator configurator) {
        super(configurator);
    }

    @Override
    public String translate(String expressionString) {
        Matcher matcher = super.variableMatcher(expressionString);
        String finaL = expressionString;
        while(matcher.find()) {
            String replaceValue = matcher.group(1);
            String path = matcher.group(2);
            finaL = finaL.replace(replaceValue, getConfigurator().internal__getValueFromPath(path, new UniversalTranslator(getConfigurator())).getValue().toString());
        }

        return finaL;
    }


}
