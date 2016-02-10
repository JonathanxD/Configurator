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
package github.therealbuggy.configurator.transformer;

/**
 * Created by jonathan on 10/02/16.
 */
public class TransformedObject<T> {

    private final T object;
    private final Class<? extends Transformer> transformer;


    public TransformedObject(T object, Class<? extends Transformer> transformer) {
        this.object = object;
        this.transformer = transformer;
    }

    public T getObject() {
        return object;
    }

    public Class<? extends Transformer> getTransformer() {
        return transformer;
    }
}
