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
package github.therealbuggy.configurator.holder;

/**
 * Created by jonathan on 02/01/16.
 */
public class ValueHolder<T> {
    @SuppressWarnings("unchecked")
    private static final ValueHolder EMPTY = new ValueHolder<>(null);

    private final T value;
    public ValueHolder(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public boolean isEmpty() {
        return this == empty() || this.value == null;
    }

    @SuppressWarnings("unchecked")
    public static <T> ValueHolder<T> empty() {
        return EMPTY;
    }
}
