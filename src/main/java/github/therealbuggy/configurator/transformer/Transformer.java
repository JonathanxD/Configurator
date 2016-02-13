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
import github.therealbuggy.configurator.nav.In;
import github.therealbuggy.configurator.transformer.exception.TransformException;
import github.therealbuggy.configurator.types.Type;
import github.therealbuggy.configurator.utils.Reference;

/**
 * Created by jonathan on 04/01/16.
 */
public interface Transformer<T> {

    /**
     * Transform ('deserialize' value) from a section (get all keys from {@link github.therealbuggy.configurator.BackEndIConfigurator#getAllOnPath(String)}) to a value
     * @param section Section
     * @param configurator Configurator related. {@link IConfigurator#getTransformedSection(In, Reference)}
     * @return {@link Optional#empty()} or a {@link Optional#of(Object)} the 'deserialized' value
     * @throws TransformException {@link TransformException}
     */
    Optional<T> transformSection(Key<?> section, IConfigurator<?> configurator) throws TransformException;

    /**
     * ('serialize' value & ) Construct a section (set all keys calling {@link github.therealbuggy.configurator.BackEndIConfigurator#setValueToPath(String, Type)})
     * @param section Section
     * @param configurator Configurator related. {@link IConfigurator#constructSection(Key, Reference)}}
     * @throws TransformException {@link TransformException}
     */

    void constructSection(Key<?> section, T value, IConfigurator<?> configurator) throws TransformException;

    /**
     *
     * @param value
     * @deprecated Don't checks support in 'transformSection' process
     * @return
     *
     */
    @Deprecated
    boolean canConstruct(Object value);

    /**
     * Determine support of an reference.
     * @param reference Reference
     * @return Support reference or not
     */
    boolean supports(Reference reference);

}
