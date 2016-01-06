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
package github.therealbuggy.configurator.key;

import github.therealbuggy.configurator.BackEndIConfigurator;
import github.therealbuggy.configurator.argument.Arguments;
import github.therealbuggy.configurator.argument.SpecificArgument;
import github.therealbuggy.configurator.exceptions.CannotApply;
import github.therealbuggy.configurator.exceptions.CannotFindPath;
import github.therealbuggy.configurator.holder.UnknownValueHolder;
import github.therealbuggy.configurator.holder.ValueHolder;
import github.therealbuggy.configurator.sections.Section;
import github.therealbuggy.configurator.translator.Translator;
import github.therealbuggy.configurator.types.Type;

public class KeyImpl<T> implements Key<T> {
    private static final Key EMPTY = new KeyImpl<>();

    private final String name;
    private final String path;
    private final Section section;
    private final Type<T> type;
    private final Translator<T> valueTranslator;
    private final Arguments arguments;
    private final BackEndIConfigurator iConfigurator;

    private KeyImpl() {
        this(null, null, null, null, null, null, null);
    }

    public KeyImpl(String name, String path, Section section, Type<T> type, Translator<T> valueTranslator, Arguments arguments, BackEndIConfigurator configurator) {
        this.name = name;
        this.path = path;
        this.section = section;
        this.type = type;
        this.valueTranslator = valueTranslator;
        this.arguments = arguments;
        this.iConfigurator = configurator;
    }

    @Override
    public Section section() {
        return this.section;
    }

    @Override
    public Type<T> getType() {
        return type;
    }

    @SuppressWarnings("unchecked")
    @Override
    public ValueHolder<T> getKnowValue() {
        if(!this.iConfigurator.valueExists(getPath())){
            throw new CannotFindPath(getPath());
        }
        return new ValueHolder<>(valueTranslator.translate(getPlainValue()));
    }

    @SuppressWarnings("unchecked")
    @Override
    public <E> ValueHolder<E> getValue() {
        if(!this.iConfigurator.valueExists(getPath())){
            throw new CannotFindPath(getPath());
        }
        return (ValueHolder<E>) new ValueHolder<>(valueTranslator.translate(getPlainValue()));
    }

    @Override
    public UnknownValueHolder getUnknownValue() {
        if(!this.iConfigurator.valueExists(getPath())){
            throw new CannotFindPath(getPath());
        }
        return new UnknownValueHolder(this.iConfigurator.getValueFromPath(getPath()));
    }

    @Override
    public BackEndIConfigurator getBackEndConfigurator() {
        return this.iConfigurator;
    }

    @Override
    public String getPath() {
        return this.path;
    }

    @Override
    public boolean isMain() {
        return this.section == null;
    }

    @Override
    public String getName() {
        return name;
    }

    @SuppressWarnings("unchecked")
    public static <T> Key<T> empty() {
        return EMPTY;
    }

    public boolean isEmpty() {
        return this == empty() || type == null;
    }

    @Override
    public Translator<T> getValueTranslator() {
        return valueTranslator;
    }

    @Override
    public T getExactValue() {
        return valueTranslator.translate(getPlainValue());
    }

    @Override
    public String getPlainValue() {
        return this.iConfigurator.getValueFromPathAsString(getPath());
    }

    @Override
    public T applyOrderedArgument(Object... inputs) {
        if(arguments == null) {
            throw new CannotApply("Cannot apply arguments to non-specified arguments");
        }
        return valueTranslator.translate(arguments.apply(getPlainValue()).applyOrdered(inputs));
    }

    @Override
    public T applyTypedArgument(Object... inputs) {
        if(arguments == null) {
            throw new CannotApply("Cannot apply arguments to non-specified arguments");
        }
        return valueTranslator.translate(arguments.apply(getPlainValue()).applyTyped(inputs));
    }

    @Override
    public T applySpecificArgument(SpecificArgument specificArgument) {
        if(arguments == null) {
            throw new CannotApply("Cannot apply arguments to non-specified arguments");
        }
        return valueTranslator.translate(arguments.apply(getPlainValue()).applySpecific(specificArgument));
    }

}
