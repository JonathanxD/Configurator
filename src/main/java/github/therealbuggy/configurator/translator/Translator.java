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
import github.therealbuggy.configurator.exceptions.CannotGetConfigurator;
import github.therealbuggy.configurator.modifiers.ArgumentBuilder;
import github.therealbuggy.configurator.modifiers.IModifierHandler;

import java.util.Objects;

public abstract class Translator<T> {

    private IConfigurator configurator;
    private ArgumentBuilder argumentBuilder = null;

    Translator() {
        this.configurator = null;
    }

    Translator(IConfigurator configurator) {
        this.configurator = Objects.requireNonNull(configurator);
    }

    final void setConfigurator(IConfigurator configurator) {
        Objects.requireNonNull(configurator);
        this.configurator = configurator;
    }

    final IConfigurator getConfigurator() {
        if(configurator != null) {
            return configurator;
        }else{
            throw new CannotGetConfigurator("Cannot get configurator! Defined? The configurator need be defined in constructor or calling setConfigurator method!");
        }

    }

    public synchronized final Translator<T> argument(ArgumentBuilder argumentBuilder) {
        this.argumentBuilder = argumentBuilder;
        return this;
    }

    synchronized ArgumentBuilder getArgumentBuilder() {
        return argumentBuilder;
    }

    synchronized boolean isArgumentPresent() {
        return argumentBuilder != null;
    }

    public T translate(String expression){
        return valueTranslate(expression);
    }

    protected abstract T valueTranslate(String expression);

    public abstract IModifierHandler getIModifierHandler();
}
