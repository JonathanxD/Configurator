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
package github.therealbuggy.configurator.translator;

import github.therealbuggy.configurator.IConfigurator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jonathan on 02/01/16.
 */
public abstract class VariableTranslator<T> extends Translator<T> {
    VariableTranslator() {
        super();
    }
    VariableTranslator(IConfigurator configurator) {
        super(configurator);
    }

    public final Matcher variableMatcher(String expressionToMatch){
        Pattern pattern = Pattern.compile("(\\(\\$(.+)\\))");
        return pattern.matcher(expressionToMatch);
    }
}
