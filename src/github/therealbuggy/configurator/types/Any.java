package github.therealbuggy.configurator.types;

import github.therealbuggy.configurator.holder.UnknownValueHolder;
import github.therealbuggy.configurator.translator.Translator;

/**
 * Created by jonathan on 01/01/16.
 */
public class Any implements Type<Object> {

    private final Object defaultValue;

    protected Any() {
        this(null);
    }

    protected Any(Object defaultValue) {
        this.defaultValue = defaultValue;
    }

    @Override
    public Object translate(UnknownValueHolder input) {
        return input.getAs(Object.class);
    }

    @Override
    public Object flyTranslator(String expression, Translator<Object> translator) {
        return translator.translate(expression);
    }

    @Override
    public Object defaultValue() {
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
