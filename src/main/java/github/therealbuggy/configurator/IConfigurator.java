package github.therealbuggy.configurator;

import java.util.Collection;

import github.therealbuggy.configurator.holder.UnknownValueHolder;
import github.therealbuggy.configurator.holder.ValueHolder;
import github.therealbuggy.configurator.key.Key;
import github.therealbuggy.configurator.nav.In;
import github.therealbuggy.configurator.translator.Translator;
import github.therealbuggy.configurator.types.Type;

/**
 * Created by jonathan on 01/01/16.
 */

/**
 * IConfigurator has 2 groups
 * 1. Group (this): External Implementations - All implementations here is an front-end to Group 2
 * 2. Group: Back-End IConfigurator: Handle your Configuration API to get Values from PATHS
 */
public interface IConfigurator<E> {

    Key<?> tagSection(E tagObject, String section);
    <T> Key<T> tagSection(E tagObject, String section, Type<T> type);
    <T> Key<T> tagSection(E tagObject, String section, Type<T> type, In<E> superSection);
    Key<?> tagSection(E tagObject, String section, In<E> superSection);
    <T> Key<T> tagSection(E tagObject, String section, In<E> superSection, Type<T> type);
    <T> Key<T> getSection(In<E> in);
    <T> Key<T> getValue(E tagObject, In<E> in);

    Collection<Key<?>> getValues(In<E> in);
    Collection<Key<?>> getSections(In<E> in);

    UnknownValueHolder internal__getValueFromPath(String pathName);
    <T> ValueHolder<T> internal__getValueFromPath(String pathName, Translator<T> translator);
}
