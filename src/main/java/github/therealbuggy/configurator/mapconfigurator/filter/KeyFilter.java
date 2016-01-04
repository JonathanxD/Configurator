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
package github.therealbuggy.configurator.mapconfigurator.filter;

import github.therealbuggy.configurator.key.Key;
import github.therealbuggy.configurator.key.KeyUtil;

/**
 * Created by jonathan on 04/01/16.
 */
public class KeyFilter<E> implements ExtraFilter<E, Key<?>> {

    private final E extraValue;

    public KeyFilter(){
        this(null);
    }

    public KeyFilter(E extraValue){
        this.extraValue = extraValue;
    }


    @Override
    public boolean filter(Key<?> value) {
        return !KeyUtil.isSection(value) && !KeyUtil.isNullKey(value);
    }

    @Override
    public boolean filter(E extraValue, Key<?> value) {
        if(this.extraValue != null){
            return filter(value) && this.extraValue.equals(extraValue);
        }
        return filter(value);
    }
}
