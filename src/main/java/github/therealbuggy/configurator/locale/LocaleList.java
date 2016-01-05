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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import github.therealbuggy.configurator.utils.Reflection;

public class LocaleList<T>  {

    private final Set<ILocale<T>> localeSet = new HashSet<>();

    @SuppressWarnings("unchecked")
    public T translate(T valueToModify) {
        List<T> values = Collections.singletonList(valueToModify);

        if(valueToModify instanceof String) {
            String stringValueToModify = (String) valueToModify;
            if(stringValueToModify.contains("|")) {
                String[] vals = stringValueToModify.split("\\|");
                values.clear();
                values = new ArrayList<>((Collection<? extends T>) Arrays.asList(vals));
            }
        }

        T valueClone = Reflection.tryClone(valueToModify);
        for(T value : values){
            for(ILocale<T> modifier : localeSet) {
                valueClone = modifier.translate(value);
            }
        }
        return valueClone;
    }

    public Collection<ILocale<T>> getLocales() {
        return Collections.unmodifiableSet(localeSet);
    }

    public void addLocale(ILocale<T> modifier) {
        localeSet.add(modifier);
    }

    public void remnoveLocale(ILocale<T> modifier) {
        localeSet.remove(modifier);
    }
}
