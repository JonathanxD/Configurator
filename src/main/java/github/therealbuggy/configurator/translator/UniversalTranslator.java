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

public class UniversalTranslator extends VariableTranslator<String> {

    UniversalTranslator() {

    }

    public UniversalTranslator(IConfigurator configurator) {
        super(configurator);
    }

    @Override
    public String valueTranslate(String expressionString) {
        Matcher matcher = super.variableMatcher(expressionString);
        String finaL = expressionString;
        while(matcher.find()) {
            String replaceValue = matcher.group(1);
            String path = matcher.group(2);
            finaL = finaL.replace(replaceValue, getConfigurator().internal__getValueFromPath(path, new UniversalTranslator(getConfigurator())).getValue().toString());
        }

        return finaL;
    }


}
