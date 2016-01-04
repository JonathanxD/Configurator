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
package github.therealbuggy.configurator.sections;

import github.therealbuggy.configurator.BackEndIConfigurator;
import github.therealbuggy.configurator.key.Key;
import github.therealbuggy.configurator.key.NullKey;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jonathan on 02/01/16.
 */
abstract class SectionNullKeyImpl<E> extends NullKey{
    private final Map<E, Key<?>> keys = new HashMap<>();
    private final Section section;
    private final BackEndIConfigurator iConfigurator;

    SectionNullKeyImpl(Section section) {
        this(section, null);
    }

    SectionNullKeyImpl(Section section, BackEndIConfigurator iConfigurator) {
        this.section = section;
        this.iConfigurator = iConfigurator;
    }

    public final void setKey(E keyTag, Key<?> key){
        keys.put(keyTag, key);
    }

    public final Key<?> getKey(E keyTag){
        return keys.get(keyTag);
    }

    public final boolean containsKey(E keyTag){
        return keys.containsKey(keyTag);
    }

    public final void removeKey(E keyTag) {
        keys.remove(keyTag);
    }

    @Override
    public boolean isMain() {
        return section == null;
    }

    @Override
    public BackEndIConfigurator getBackEndConfigurator() {
        return this.iConfigurator;
    }

    public Map<E, Key<?>> getKeys() {
        return keys;
    }
}
