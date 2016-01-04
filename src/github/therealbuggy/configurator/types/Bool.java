package github.therealbuggy.configurator.types;

import github.therealbuggy.configurator.holder.UnknownValueHolder;
import github.therealbuggy.configurator.translator.Translator;

/**
 * Created by jonathan on 01/01/16.
 */
public class Bool implements Type<Boolean> {

    private final Boolean defaultValue;

    protected Bool() {
        this(null);
    }

    protected Bool(Boolean defaultValue) {
        this.defaultValue = defaultValue;
    }

    @Override
    public Boolean translate(UnknownValueHolder input) {
        return input.getAsBoolean();
    }

    @Override
    public Boolean flyTranslator(String expression, Translator<Boolean> translator) {
        return translator.translate(expression);
    }

    @Override
    public Boolean defaultValue() {
        if (defaultValue == null)
            throw new NullPointerException("Cannot return defaultValue!!!");

        return defaultValue;
    }

    @Override
    public boolean isTranslatorSupported() {
        return true;
    }

    @Override
    public boolean isDefaultValuePresent() {
        return defaultValue != null;
    }

    @Override
    public String toString() {
        return TypeBuilder.TypeHelper.typeToString(this);
    }
}
