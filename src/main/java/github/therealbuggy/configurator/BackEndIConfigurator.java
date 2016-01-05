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


import java.util.Map;

import github.therealbuggy.configurator.types.Type;

/**
 * Created by jonathan on 01/01/16.
 */
public interface BackEndIConfigurator {

    /**
     * Define new value
     * @param path Path to value
     */
    void setValueToPath(String path, Type<?> valueType);

    boolean valueExists(String path);

    /**
     * Get value in the PATH
     * @param path Path to value
     * @return Value
     */
    Object getValueFromPath(String path);

    /**
     * Get value IN Path as {@link String}
     * @param path Path to value
     * @return The value as {@link String}
     */
    String getValueFromPathAsString(String path);

    /**
     * Get all values in determinate path
     * @param path Path to values
     * @return Values
     */
    Map<String, Object> getValuesOnPath(String path);

    /**
     * Get all paths of sections in determinate path
     * @param path Path to Sections
     * @return Sections path
     */
    Map<String, Object> getSectionsOnPath(String path);

}
