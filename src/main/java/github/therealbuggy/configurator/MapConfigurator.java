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
package github.therealbuggy.configurator;

import github.therealbuggy.configurator.exceptions.NotInsideSection;
import github.therealbuggy.configurator.holder.UnknownValueHolder;
import github.therealbuggy.configurator.holder.ValueHolder;
import github.therealbuggy.configurator.key.Key;
import github.therealbuggy.configurator.key.KeyImpl;
import github.therealbuggy.configurator.mapconfigurator.filter.ExtraFilter;
import github.therealbuggy.configurator.mapconfigurator.filter.KeyFilter;
import github.therealbuggy.configurator.mapconfigurator.filter.OnlyExtraFilter;
import github.therealbuggy.configurator.mapconfigurator.filter.SectionFilter;
import github.therealbuggy.configurator.nav.In;
import github.therealbuggy.configurator.sections.Section;
import github.therealbuggy.configurator.translator.Translator;
import github.therealbuggy.configurator.types.Type;

import java.util.*;

public abstract class MapConfigurator<E> implements IConfigurator<E>{
    private final Map<E, Key<?>> sectionsAndKeys = new HashMap<>();
    private final BackEndIConfigurator backEndIConfigurator;

    MapConfigurator(BackEndIConfigurator backEndIConfigurator) {
        this.backEndIConfigurator = backEndIConfigurator;
    }

    @Override
    public Key<?> tagSection(E tagName, String section) {
        return this.tagSection(tagName, section, null, In.<E>main());
    }

    @Override
    public <T> Key<T> tagSection(E tagName, String section, Type<T> type) {
        return this.tagSection(tagName, section, type, In.<E>main());
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> Key<T> tagSection(E tagName, String section, Type<T> type, In<E> superSection) {

        Section supSection = null;
        if(superSection != null){
            Key<?> key = null;
            if(!superSection.isMain()){
                key = getSection(superSection);
            }
            if(key != null){
                if(key instanceof Section) {
                    supSection = (Section) key;
                }
            }
        }

        if(type != null){
            String path = (supSection != null ? supSection.getPath() + "." + section : section);

            Key<T> key = new KeyImpl<>(section, path, supSection, type, this.backEndIConfigurator);
            if(!backEndIConfigurator.valueExists(key.getPath())) {
                backEndIConfigurator.setValueToPath(key.getPath(), type);
            }
            if(supSection != null){
                supSection.setKey(tagName, key);
            }else{
                sectionsAndKeys.put(tagName, key);
            }
            return key;
        } else {
            Section sectionInstance = new Section(section, supSection, this.backEndIConfigurator);

            if(supSection != null){
                supSection.setKey(tagName, sectionInstance);
            }else{
                sectionsAndKeys.put(tagName, sectionInstance);
            }

            return sectionInstance;
        }

    }

    @Override
    public Key<?> tagSection(E tagName, String section, In<E> superSection) {
        return this.tagSection(tagName, section, null, superSection);
    }

    @Override
    public <T> Key<T> tagSection(E tagName, String section, In<E> superSection, Type<T> type) {
        return this.tagSection(tagName, section, type, superSection);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> Key<T> getSection(In<E> in) {
        LinkedList<E> linkedList = new LinkedList<>(Arrays.asList(in.getPath()));
        E first = linkedList.removeLast();
        E[] path = (E[]) linkedList.toArray(new Object[linkedList.size()]);
        return getValue(first, In.path(path));
    }

    @SuppressWarnings({"unchecked", "LoopStatementThatDoesntLoop"})
    @Override
    public <T> Key<T> getValue(E tagName, In<E> in) {
        Objects.requireNonNull(in);
        Collection<Key<?>> keys = getFiltering(in, new OnlyExtraFilter<>(tagName));

        for(Key<?> key : keys){
            return (Key<T>) key;
        }

        return KeyImpl.empty();
    }

    @Override
    public Collection<Key<?>> getValues(In<E> in) {
        return getFiltering(in, new KeyFilter<E>());
    }


    @Override
    public Collection<Key<?>> getSections(In<E> in) {
        return getFiltering(in, new SectionFilter<E>());
    }

    @SuppressWarnings("unchecked")
    private Collection<Key<?>> getFiltering(In<E> in, ExtraFilter<E, Key<?>> filter) {
        Objects.requireNonNull(in);
        Map<E, Key<?>> map = null;
        Collection<Key<?>> returnKeys;

        if(!in.isMain()){
            E[] path = in.getPath();
            int x = 0;
            E aliasName = path[0];
            Section sec;
            if(sectionsAndKeys.containsKey(aliasName)) {
                Key<?> checkKey = sectionsAndKeys.get(aliasName);

                if(checkKey instanceof Section) {
                    sec = (Section) checkKey;
                }else {
                    return Collections.emptyList();
                }

                while(x + 1 < path.length){
                    ++x;
                    aliasName = path[x];
                    if(sec.containsKey(aliasName) && (checkKey = sec.getKey(aliasName)) instanceof Section){
                        sec = (Section) checkKey;
                    }else{
                        return Collections.emptyList();
                    }
                }

                map = (Map<E, Key<?>>) sec.getKeys();
            }
        }else{
            map = sectionsAndKeys;
        }
        if(map != null){
            returnKeys = new ArrayList<>();
            for(Map.Entry<?, Key<?>> entry : map.entrySet()){
                if(filter.filter((E) entry.getKey(), entry.getValue())) {
                   returnKeys.add(entry.getValue());
                }
            }
            return returnKeys;
        }
        return Collections.emptyList();
    }

    @Override
    public UnknownValueHolder internal__getValueFromPath(String pathName) {
        return new UnknownValueHolder(backEndIConfigurator.getValueFromPath(pathName));
    }

    @Override
    public <T> ValueHolder<T> internal__getValueFromPath(String pathName, Translator<T> translator) {
        return new ValueHolder<>(translator.translate(String.valueOf(backEndIConfigurator.getValueFromPath(pathName))));
    }


}
