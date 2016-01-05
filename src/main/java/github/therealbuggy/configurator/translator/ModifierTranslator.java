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
import github.therealbuggy.configurator.modifiers.ArgumentBuilder;
import github.therealbuggy.configurator.modifiers.IModifierHandler;


public abstract class ModifierTranslator<T> extends Translator<T> {


    ModifierTranslator() {
        super();
    }
    ModifierTranslator(IConfigurator configurator) {
        super(configurator);
    }

    @SuppressWarnings("unchecked")
    @Override
    public T translate(String expression) {
        IModifierHandler<String> modifierHandler = getConfigurator().getModifierHandler();
        String modExpression = modifierHandler.modify(expression);
        if(isArgumentPresent()) {
            modExpression = getArgumentBuilder().build(modExpression);
        }
        return valueTranslate(modExpression);
    }

    @SuppressWarnings("unchecked")
    @Override
    public IModifierHandler<String> getIModifierHandler() {
        return getConfigurator().getModifierHandler();
    }
}
