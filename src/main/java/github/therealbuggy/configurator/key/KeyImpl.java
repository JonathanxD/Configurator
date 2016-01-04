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
import github.therealbuggy.configurator.holder.UnknownValueHolder;
import github.therealbuggy.configurator.holder.ValueHolder;
import github.therealbuggy.configurator.sections.Section;
import github.therealbuggy.configurator.translator.Translator;
import github.therealbuggy.configurator.types.Type;

/**
 * Created by jonathan on 02/01/16.
 */
public class KeyImpl<T> implements Key<T> {
    private static final Key EMPTY = new KeyImpl<>();

    private final String path;
    private final Section section;
    private final Type<T> type;
    private final BackEndIConfigurator iConfigurator;

    private KeyImpl() {
        this(null, null, null, null);
    }

    public KeyImpl(String path, Type<T> type) {
        this(path, null, type, null);
    }

    public KeyImpl(String path, Section section, Type<T> type) {
        this(path, section, type, null);
    }

    public KeyImpl(String path, Section section, Type<T> type, BackEndIConfigurator configurator) {
        this.path = path;
        this.section = section;
        this.type = type;
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

    @Override
    public ValueHolder<T> getKnowValue(Translator<T> translator) {
        return new ValueHolder<>(translator.translate(this.iConfigurator.getValueFromPathAsString(getPath())));
    }

    @Override
    public <E> ValueHolder<E> getValue(Translator<E> translator) {
        return new ValueHolder<>(translator.translate(this.iConfigurator.getValueFromPathAsString(getPath())));
    }

    @Override
    public UnknownValueHolder getValue() {
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

    public static <T> Key<T> empty() {
        return EMPTY;
    }

    public boolean isEmpty() {
        return this == empty() || type == null;
    }
}
