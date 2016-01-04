package github.therealbuggy.configurator.translator.statics;

import github.therealbuggy.configurator.IConfigurator;
import github.therealbuggy.configurator.translator.IntTranslator;
import github.therealbuggy.configurator.translator.Translator;
import github.therealbuggy.configurator.translator.UniversalTranslator;

import java.util.Objects;

/**
 * Created by jonathan on 02/01/16.
 */
public class DefaultTranslators {

    private final IConfigurator iConfigurator;

    public DefaultTranslators(IConfigurator iConfigurator) {
        this.iConfigurator = Objects.requireNonNull(iConfigurator);
    }

    public Translator<Integer> getIntTranslator() {
        return getIntTranslator(this.iConfigurator);
    }

    public Translator<String> getUniversalTranslator() {
        return getUniversalTranslator(this.iConfigurator);
    }

    public static Translator<Integer> getIntTranslator(IConfigurator configurator) {
        return new IntTranslator(configurator);
    }

    public static Translator<String> getUniversalTranslator(IConfigurator configurator) {
        return new UniversalTranslator(configurator);
    }
}
