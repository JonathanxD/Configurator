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
package github.therealbuggy.configurator.modifiers;

import java.util.HashMap;
import java.util.Map;

import github.therealbuggy.configurator.utils.Reflection;

/**
 * Created by jonathan on 05/01/16.
 */
public class ArgumentBuilder  {

    private final Map<String, String> arguments = new HashMap<>();
    private final ModifierHandlerImpl<String> argumentModifier = new ModifierHandlerImpl<>();

    private ArgumentBuilder() {}

    public ArgumentBuilder setArgument(String regex, String value) {
        arguments.put(regex, value);
        return this;
    }

    public ArgumentBuilder with(IModifier<String> modifier) {
        argumentModifier.addModifier(modifier);
        return this;
    }

    public String build(String input) {
        String inputClone = Reflection.tryClone(input);
        inputClone = argumentModifier.modify(inputClone);

        for(Map.Entry<String, String> entry : arguments.entrySet()) {
            inputClone = inputClone.replaceAll(entry.getKey(), entry.getValue());
        }

        return inputClone;
    }

    public static ArgumentBuilder argument(String regex, String value) {
        ArgumentBuilder arguments = new ArgumentBuilder();
        arguments.setArgument(regex, value);
        return arguments;
    }
}
