package github.therealbuggy.configurator.sections;

import github.therealbuggy.configurator.BackEndIConfigurator;
import github.therealbuggy.configurator.key.Key;
import github.therealbuggy.configurator.key.NullKey;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jonathan on 02/01/16.
 */
abstract class SectionNullKeyImpl<E> extends NullKey{
    private final Map<E, Key<?>> keys = new HashMap<>();
    private final Section section;
    private final BackEndIConfigurator iConfigurator;

    SectionNullKeyImpl(Section section) {
        this(section, null);
    }

    SectionNullKeyImpl(Section section, BackEndIConfigurator iConfigurator) {
        this.section = section;
        this.iConfigurator = iConfigurator;
    }

    public final void setKey(E keyTag, Key<?> key){
        keys.put(keyTag, key);
    }

    public final Key<?> getKey(E keyTag){
        return keys.get(keyTag);
    }

    public final boolean containsKey(E keyTag){
        return keys.containsKey(keyTag);
    }

    public final void removeKey(E keyTag) {
        keys.remove(keyTag);
    }

    @Override
    public boolean isMain() {
        return section == null;
    }

    @Override
    public BackEndIConfigurator getBackEndConfigurator() {
        return this.iConfigurator;
    }

    public Map<E, Key<?>> getKeys() {
        return keys;
    }
}
