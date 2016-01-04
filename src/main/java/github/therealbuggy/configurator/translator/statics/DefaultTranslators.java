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
package github.therealbuggy.configurator.translator.statics;

import github.therealbuggy.configurator.IConfigurator;
import github.therealbuggy.configurator.translator.IntTranslator;
import github.therealbuggy.configurator.translator.Translator;
import github.therealbuggy.configurator.translator.UniversalTranslator;

import java.util.Objects;

/**
 * Created by jonathan on 02/01/16.
 */
public class DefaultTranslators {

    private final IConfigurator iConfigurator;

    public DefaultTranslators(IConfigurator iConfigurator) {
        this.iConfigurator = Objects.requireNonNull(iConfigurator);
    }

    public Translator<Integer> getIntTranslator() {
        return getIntTranslator(this.iConfigurator);
    }

    public Translator<String> getUniversalTranslator() {
        return getUniversalTranslator(this.iConfigurator);
    }

    public static Translator<Integer> getIntTranslator(IConfigurator configurator) {
        return new IntTranslator(configurator);
    }

    public static Translator<String> getUniversalTranslator(IConfigurator configurator) {
        return new UniversalTranslator(configurator);
    }
}
