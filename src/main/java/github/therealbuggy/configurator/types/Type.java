package github.therealbuggy.configurator.types;

import github.therealbuggy.configurator.holder.UnknownValueHolder;
import github.therealbuggy.configurator.translator.Translator;

/**
 * Created by jonathan on 01/01/16.
 */
public interface Type<T> {
    T translate(UnknownValueHolder input);
    T flyTranslator(String expression, Translator<T> translator);
    T defaultValue();

    boolean isTranslatorSupported();
    boolean isDefaultValuePresent();

}
