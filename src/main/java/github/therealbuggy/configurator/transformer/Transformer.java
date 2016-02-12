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

import java.util.Optional;

import github.therealbuggy.configurator.IConfigurator;
import github.therealbuggy.configurator.key.Key;

/**
 * Created by jonathan on 04/01/16.
 */
public interface Transformer<T> {

    Optional<T> transformSection(Key<?> section, IConfigurator<?> configurator);

    void constructSection(Key<?> section, T value, IConfigurator<?> configurator);

    boolean canConstruct(Class<?> valueClass);


}
