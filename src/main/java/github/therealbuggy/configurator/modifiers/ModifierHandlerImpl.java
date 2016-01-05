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
package github.therealbuggy.configurator.modifiers;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import github.therealbuggy.configurator.utils.Reflection;

public class ModifierHandlerImpl<T> implements IModifierHandler<T> {

    private final Set<IModifier<T>> modifierSet = new HashSet<>();

    @Override
    public T modify(T valueToModify) {
        T valueClone = Reflection.tryClone(valueToModify);
        // Auto translate

        for(IModifier<T> modifier : modifierSet) {
            T tmp = modifier.getLocale().translate(valueToModify);
            if(tmp != null){
                valueClone = tmp;
            }
            tmp = modifier.modify(valueClone);
            if(tmp != null){
                valueClone = tmp;
            }

        }
        return valueClone;
    }

    @Override
    public Collection<IModifier<T>> getModifiers() {
        return Collections.unmodifiableSet(modifierSet);
    }

    @Override
    public void addModifier(IModifier<T> modifier) {
        modifierSet.add(modifier);
    }

    @Override
    public void removeModifier(IModifier modifier) {
        modifierSet.remove(modifier);
    }
}
