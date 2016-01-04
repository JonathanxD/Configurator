package github.therealbuggy.configurator.mapconfigurator.filter;

import github.therealbuggy.configurator.key.Key;
import github.therealbuggy.configurator.key.KeyUtil;

/**
 * Created by jonathan on 04/01/16.
 */
public class SectionFilter implements Filter<Key<?>> {
    @Override
    public boolean filter(Key<?> value) {
        return KeyUtil.isSection(value);
    }
}
