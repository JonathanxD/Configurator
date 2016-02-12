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
package github.therealbuggy.configurator.locale;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import github.therealbuggy.configurator.utils.Reflection;

public class LocaleList<T, ID> {

    private final List<ILocale<T, ID>> localeList = new ArrayList<>();


    @SuppressWarnings("unchecked")
    public T translate(T valueToModify) {
        return (T) translateType(valueToModify, Type.VALUE);
    }

    @SuppressWarnings("unchecked")
    public ID translateId(T valueToModify) {
        return (ID) translateType(valueToModify, Type.ID);
    }

    public T translateFromId(ID id) {
        T value = null;

        for (ILocale<T, ID> modifier : localeList) {

            T tmp = modifier.translate(id);
            if (tmp != null)
                value = tmp;

        }
        return value;
    }

    @SuppressWarnings("unchecked")
    public Object translateType(T valueToModify, Type type) {
        List<T> values = Collections.singletonList(valueToModify);

        if (valueToModify instanceof String) {
            String stringValueToModify = (String) valueToModify;
            if (stringValueToModify.contains("|")) {
                String[] vals = stringValueToModify.split("\\|");
                values.clear();
                values = new ArrayList<>((Collection<? extends T>) Arrays.asList(vals));
            }
        }

        T valueClone = Reflection.tryClone(valueToModify);
        ID id = null;

        for (T value : values) {
            for (ILocale<T, ID> modifier : localeList) {

                T tmp0 = LocaleHelper.retTranslate(modifier, value);
                if (tmp0 != null) {
                    valueClone = tmp0;
                }

                ID tmp = LocaleHelper.getID(modifier, value);
                if (tmp != null) {
                    id = tmp;
                }
            }
        }

        if (type == Type.VALUE) {
            return valueClone;
        } else {
            return id;
        }
    }

    public Collection<ILocale<T, ID>> getLocales() {
        return Collections.unmodifiableList(localeList);
    }

    public void addLocale(ILocale<T, ID> modifier) {
        if (!localeList.contains(modifier))
            localeList.add(modifier);
    }

    public void removeLocale(ILocale<T, ID> modifier) {
        localeList.remove(modifier);
    }

    enum Type {
        ID,
        VALUE
    }
}
