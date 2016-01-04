package github.therealbuggy.configurator.types;

import github.therealbuggy.configurator.holder.UnknownValueHolder;
import github.therealbuggy.configurator.translator.Translator;

/**
 * Created by jonathan on 01/01/16.
 */
public class Int implements Type<Integer> {
    private final Integer defaultValue;

    protected Int() {
        this(null);
    }

    protected Int(Integer defaultValue) {
        this.defaultValue = defaultValue;
    }

    @Override
    public Integer translate(UnknownValueHolder input) {
        return input.getAsInt();
    }

    @Override
    public Integer flyTranslator(String expression, Translator<Integer> translator) {
        return translator.translate(expression);
    }

    @Override
    public boolean isTranslatorSupported() {
        return true;
    }

    @Override
    public Integer defaultValue() {
        if (defaultValue == null)
            throw new NullPointerException("Cannot return defaultValue!!!");

        return defaultValue;
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
