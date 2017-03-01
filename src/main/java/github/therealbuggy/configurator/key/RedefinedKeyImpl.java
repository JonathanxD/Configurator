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

import github.therealbuggy.configurator.IConfigurator;
import github.therealbuggy.configurator.argument.Arguments;
import github.therealbuggy.configurator.argument.SpecificArgument;
import github.therealbuggy.configurator.exceptions.CannotApply;
import github.therealbuggy.configurator.exceptions.CannotFindPath;
import github.therealbuggy.configurator.holder.UnknownValueHolder;
import github.therealbuggy.configurator.holder.ValueHolder;
import github.therealbuggy.configurator.nav.In;
import github.therealbuggy.configurator.sections.Section;
import github.therealbuggy.configurator.translator.Translator;
import github.therealbuggy.configurator.types.Value;

public class RedefinedKeyImpl<T> implements Key<T> {
    private static final Key EMPTY = new RedefinedKeyImpl<>();

    private final String name;
    private final String path;
    private final In<?> inPath;
    private final Section section;
    private final Translator<T> valueTranslator;
    private final Arguments arguments;
    private final IConfigurator<?> iConfigurator;
    private Value<T> value;

    private RedefinedKeyImpl() {
        this(null, null, null, null, null, null, null, null);
    }

    public RedefinedKeyImpl(String name, In<?> inPath, String path, Section section, Value<T> value, Translator<T> valueTranslator, Arguments arguments, IConfigurator<?> configurator) {
        this.name = name;
        this.inPath = inPath;
        this.path = path;
        this.section = section;
        this.value = value;
        this.valueTranslator = valueTranslator;
        this.arguments = arguments;
        this.iConfigurator = configurator;
    }

    @SuppressWarnings("unchecked")
    public static <T> Key<T> empty() {
        return EMPTY;
    }

    @Override
    public Section section() {
        return this.section;
    }

    public Value<T> getDefinedValue() {
        return value;
    }

    @SuppressWarnings("unchecked")
    @Override
    public ValueHolder<T> getKnowValue() {
        if (!this.getBackendConfiguration().pathExists(getPath())) {
            throw new CannotFindPath(getPath());
        }
        return new ValueHolder<>(valueTranslator.translate(getPlainValue()));
    }

    @SuppressWarnings("unchecked")
    @Override
    public <E> ValueHolder<E> getValue() {
        if (!this.getBackendConfiguration().pathExists(getPath())) {
            throw new CannotFindPath(getPath());
        }
        return (ValueHolder<E>) new ValueHolder<>(valueTranslator.translate(getPlainValue()));
    }

    @Override
    public UnknownValueHolder getUnknownValue() {
        if (!this.getBackendConfiguration().pathExists(getPath())) {
            throw new CannotFindPath(getPath());
        }
        return new UnknownValueHolder(this.getIConfigrator().getBackEndIConfigurator().getValueFromPath(getPath()));
    }

    @Override
    public IConfigurator<?> getIConfigrator() {
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

    public boolean isEmpty() {
        return this == empty() || value == null;
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
        return this.getBackendConfiguration().getValueFromPathAsString(getPath());
    }

    @Override
    public T applyOrderedArgument(Object... inputs) {
        if (arguments == null) {
            throw new CannotApply("Cannot apply arguments to non-specified arguments");
        }
        return valueTranslator.translate(arguments.apply(getPlainValue()).applyOrdered(inputs));
    }

    @Override
    public T applyTypedArgument(Object... inputs) {
        if (arguments == null) {
            throw new CannotApply("Cannot apply arguments to non-specified arguments");
        }
        return valueTranslator.translate(arguments.apply(getPlainValue()).applyTyped(inputs));
    }

    @Override
    public T applySpecificArgument(SpecificArgument specificArgument) {
        if (arguments == null) {
            throw new CannotApply("Cannot apply arguments to non-specified arguments");
        }
        return valueTranslator.translate(arguments.apply(getPlainValue()).applySpecific(specificArgument));
    }


    @SuppressWarnings("unchecked")
    @Override
    public <E> In<E> getInPath() {
        return (In<E>) inPath;
    }

    @Override
    public void defineValue(Value<T> value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Key[" +
                "name=" + getName()
                + ", path=" + getPath()
                + ", value=" + getDefinedValue().defaultValue()
                + "]";
    }
}
