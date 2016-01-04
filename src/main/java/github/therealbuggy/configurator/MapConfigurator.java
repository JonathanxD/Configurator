package github.therealbuggy.configurator;

import github.therealbuggy.configurator.exceptions.NotInsideSection;
import github.therealbuggy.configurator.holder.UnknownValueHolder;
import github.therealbuggy.configurator.holder.ValueHolder;
import github.therealbuggy.configurator.key.Key;
import github.therealbuggy.configurator.key.KeyImpl;
import github.therealbuggy.configurator.key.KeyUtil;
import github.therealbuggy.configurator.mapconfigurator.filter.Filter;
import github.therealbuggy.configurator.mapconfigurator.filter.KeyFilter;
import github.therealbuggy.configurator.mapconfigurator.filter.SectionFilter;
import github.therealbuggy.configurator.nav.In;
import github.therealbuggy.configurator.sections.Section;
import github.therealbuggy.configurator.translator.Translator;
import github.therealbuggy.configurator.types.Type;

import java.util.*;

/**
 * Created by jonathan on 01/01/16.
 */
public abstract class MapConfigurator<E> implements IConfigurator<E>{
    private final Map<E, Key<?>> sectionsAndKeys = new HashMap<>();
    @Deprecated
    private final boolean insideSection;
    private final BackEndIConfigurator backEndIConfigurator;

    /**
     * @param insideSection Deprecated (may not work correctly if use this constructor)
     * @param backEndIConfigurator
     */
    MapConfigurator(@Deprecated boolean insideSection, BackEndIConfigurator backEndIConfigurator) {
        this.insideSection = insideSection;
        this.backEndIConfigurator = backEndIConfigurator;
    }

    MapConfigurator(BackEndIConfigurator backEndIConfigurator) {
        this.insideSection = true;
        this.backEndIConfigurator = backEndIConfigurator;
    }

    @Override
    public Key<?> tagSection(E tagName, String section) {
        return this.tagSection(tagName, section, null, In.MAIN);
    }

    @Override
    public <T> Key<T> tagSection(E tagName, String section, Type<T> type) {
        return this.tagSection(tagName, section, type, In.MAIN);
    }

    @Override
    public <T> Key<T> tagSection(E tagName, String section, Type<T> type, In<E> superSection) {

        Section supSection = null;
        if(superSection != null){
            Key<?> key = null;
            if(!insideSection && !superSection.isMain()){
                key = sectionsAndKeys.get(superSection.getPath()[0]);
            }else if(insideSection && !superSection.isMain()){
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

            Key<T> key = new KeyImpl<>(path, supSection, type, this.backEndIConfigurator);
            if(!backEndIConfigurator.valueExists(key.getPath())) {
                backEndIConfigurator.setValueToPath(key.getPath(), type);
            }
            if(supSection != null && insideSection){
                supSection.setKey(tagName, key);
            }else{
                sectionsAndKeys.put(tagName, key);
            }
            return key;
        } else {
            Section sectionInstance = new Section(section, supSection, this.backEndIConfigurator);

            if(supSection != null && insideSection){
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

    @Deprecated
    public boolean isInsideSection() {
        return insideSection;
    }

    @Override
    public <T> Key<T> getSection(In<E> in) {
        LinkedList<E> linkedList = new LinkedList<>(Arrays.asList(in.getPath()));
        E first = linkedList.removeLast();
        E[] path = (E[]) linkedList.toArray(new Object[linkedList.size()]);
        return getValue(first, In.path(path));
    }

    @Override
    public <T> Key<T> getValue(E tagName, In<E> in) {
        Objects.requireNonNull(in);

        if(in.isMain()) {
            if(sectionsAndKeys.containsKey(tagName)) {
                Key<?> key = sectionsAndKeys.get(tagName);
                return (Key<T>) key;
            } else {
                return KeyImpl.empty();
            }
        }else{
            if(!insideSection) {
                throw new NotInsideSection("Cannot navigate through sections if insideSection = false");
            }
            E[] path = in.getPath();
            int x = 0;
            E aliasName = path[0];
            Section sec = null;
            if(sectionsAndKeys.containsKey(aliasName)) {
                Key<?> checkKey = sectionsAndKeys.get(aliasName);

                if(checkKey instanceof Section) {
                    sec = (Section) checkKey;
                }else {
                    return KeyImpl.empty();
                }

                while(x + 1 < path.length){
                    ++x;
                    aliasName = path[x];
                    if(sec.containsKey(aliasName) && (checkKey = sec.getKey(aliasName)) instanceof Section){
                        sec = (Section) checkKey;
                    }else{
                        return KeyImpl.empty();
                    }
                }

                if(sec.containsKey(tagName)) {
                    return sec.getKey(tagName);
                }else{
                    return KeyImpl.empty();
                }
            }
        }
        return KeyImpl.empty();

    }



    @SuppressWarnings("unchecked")
    @Override
    public Collection<Key<?>> getValues(In<E> in) {
        return getFiltering(in, new KeyFilter());
    }


    @Override
    public Collection<Key<?>> getSections(In<E> in) {
        return getFiltering(in, new SectionFilter());
    }

    private Collection<Key<?>> getFiltering(In<E> in, Filter<Key<?>> filter) {
        Objects.requireNonNull(in);
        Map<E, Key<?>> map = null;
        Collection<Key<?>> returnKeys;

        if(!in.isMain()){
            if(!insideSection) {
                throw new NotInsideSection("Cannot navigate through sections if insideSection = false");
            }
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
            returnKeys = new ArrayList<>(map.values());

            Iterator<Key<?>> keyIterator = returnKeys.iterator();
            while (keyIterator.hasNext()) {
                Key<?> key = keyIterator.next();
                if(!filter.filter(key)) {
                    keyIterator.remove();
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
