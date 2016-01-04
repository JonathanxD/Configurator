package github.therealbuggy.configurator.mapconfigurator.filter;

import github.therealbuggy.configurator.key.Key;
import github.therealbuggy.configurator.key.KeyUtil;

/**
 * Created by jonathan on 04/01/16.
 */
public class SectionFilter<E> implements ExtraFilter<E, Key<?>> {

    private final E extraValue;

    public SectionFilter() {
        this(null);
    }

    public SectionFilter(E extraValue) {
        this.extraValue = extraValue;
    }

    @Override
    public boolean filter(Key<?> value) {
        return KeyUtil.isSection(value);
    }

    @Override
    public boolean filter(E extraValue, Key<?> value) {
        if(this.extraValue != null){
            return  filter(value) && this.extraValue.equals(extraValue);
        }
        return filter(value);
    }
}
