package github.therealbuggy.configurator.translator;

import github.therealbuggy.configurator.IConfigurator;
import github.therealbuggy.configurator.exceptions.CannotGetConfigurator;

import java.util.Objects;

/**
 * Created by jonathan on 02/01/16.
 */
public abstract class Translator<T> {

    private IConfigurator configurator;

    Translator() {
        this.configurator = null;
    }

    Translator(IConfigurator configurator) {
        this.configurator = Objects.requireNonNull(configurator);
    }

    final void setConfigurator(IConfigurator configurator) {
        Objects.requireNonNull(configurator);
        this.configurator = configurator;
    }

    final IConfigurator getConfigurator() {
        if(configurator != null) {
            return configurator;
        }else{
            throw new CannotGetConfigurator("Cannot get configurator! Defined? The configurator need be defined in constructor or calling setConfigurator method!");
        }

    }

    public abstract T translate(String expression);

}
