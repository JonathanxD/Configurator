/*
 * 	Configurator - Easy way to manage configurations (for Bukkit)
 *     Copyright (C) 2016 TheRealBuggy/JonathanxD (https://github.com/JonathanxD/) <jonathan.scripter@programmer.net>
 *
 * 	GNU GPLv3
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Affero General Public License as published
 *     by the Free Software Foundation.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Affero General Public License for more details.
 *
 *     You should have received a copy of the GNU Affero General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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
