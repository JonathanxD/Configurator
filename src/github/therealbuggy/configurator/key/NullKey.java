package github.therealbuggy.configurator.key;

import github.therealbuggy.configurator.holder.UnknownValueHolder;
import github.therealbuggy.configurator.holder.ValueHolder;
import github.therealbuggy.configurator.translator.Translator;
import github.therealbuggy.configurator.types.Type;

/**
 * Created by jonathan on 02/01/16.
 */
public abstract class NullKey implements Key {
    @Override
    public Type getType() {
        return null;
    }

    @Override
    public ValueHolder getKnowValue(Translator translator) {
        return null;
    }

    @Override
    public ValueHolder getValue(Translator translator) {
        return null;
    }

    @Override
    public UnknownValueHolder getValue() {
        return null;
    }


}
