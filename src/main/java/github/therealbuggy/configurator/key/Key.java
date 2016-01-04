package github.therealbuggy.configurator.key;

import github.therealbuggy.configurator.BackEndIConfigurator;
import github.therealbuggy.configurator.holder.UnknownValueHolder;
import github.therealbuggy.configurator.holder.ValueHolder;
import github.therealbuggy.configurator.sections.Section;
import github.therealbuggy.configurator.translator.Translator;
import github.therealbuggy.configurator.types.Type;

/**
 * Created by jonathan on 02/01/16.
 */
public interface Key<T> {

    Section section();
    Type<T> getType();
    ValueHolder<T> getKnowValue(Translator<T> translator);
    UnknownValueHolder getValue();

    BackEndIConfigurator getBackEndConfigurator();
    String getPath();
    boolean isMain();

    <E> ValueHolder<E> getValue(Translator<E> translator);

}
