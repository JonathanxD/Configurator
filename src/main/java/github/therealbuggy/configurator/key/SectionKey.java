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

import java.util.Optional;

import github.therealbuggy.configurator.BackEndIConfigurator;
import github.therealbuggy.configurator.IConfigurator;
import github.therealbuggy.configurator.argument.SpecificArgument;
import github.therealbuggy.configurator.holder.UnknownValueHolder;
import github.therealbuggy.configurator.holder.ValueHolder;
import github.therealbuggy.configurator.nav.In;
import github.therealbuggy.configurator.sections.Section;
import github.therealbuggy.configurator.transformer.TransformedObject;
import github.therealbuggy.configurator.transformer.Transformer;
import github.therealbuggy.configurator.translator.Translator;
import github.therealbuggy.configurator.types.Value;

public interface SectionKey<T> {

    Section section();

    Value<T> getDefinedValue();

    @Deprecated
    ValueHolder<T> getKnowValue();

    UnknownValueHolder getUnknownValue();

    @Deprecated
    Translator<T> getValueTranslator();

    T getExactValue();

    String getPlainValue();

    T applyOrderedArgument(Object... inputs);

    T applyTypedArgument(Object... inputs);

    T applySpecificArgument(SpecificArgument specificArgument);

    IConfigurator<?> getIConfigrator();

    default BackEndIConfigurator getBackendConfiguration() {
        return getIConfigrator().getBackEndIConfigurator();
    }

    default void setValue(Value<T> value) {
        this.getIConfigrator().setValue(this, value);
    }

    default void setValueToType(Value<T> value) {
        this.getIConfigrator().constructSection(this, value.getGenericRepresentation());
    }

    @SuppressWarnings("unchecked")
    default Optional<TransformedObject<T>> getTransformed() {

        Optional<TransformedObject<Object>> transformedSection = this.getIConfigrator().getTransformedSection(this.getInPath(), this.getDefinedValue().getGenericRepresentation());

        return transformedSection.isPresent() ? Optional.of((TransformedObject<T>)transformedSection.get()) : Optional.empty();
    }

    @SuppressWarnings("unchecked")
    default Transformer<?, ?, ?> getTransformer() {
        return (Transformer<?, ?, ?>) this.getIConfigrator().getTransformerHandler().getTransformer(this, this.getDefinedValue().getGenericRepresentation()).orElse(null);
    }

    void defineValue(Value<T> value);

    String getPath();

    String getName();

    boolean isMain();

    <E> ValueHolder<E> getValue();

    <E> In<E> getInPath();


    static SectionKey getNewValued(SectionKey key, Value newValue) {
        return new RedefinedKeyImpl(key, newValue);
    }
}
