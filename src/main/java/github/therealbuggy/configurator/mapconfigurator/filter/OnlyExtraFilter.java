package github.therealbuggy.configurator.mapconfigurator.filter;

import github.therealbuggy.configurator.key.Key;

/**
 * Created by jonathan on 04/01/16.
 */
public class OnlyExtraFilter<E> implements ExtraFilter<E, Key<?>> {

    private final E extraValue;

    public OnlyExtraFilter(){
        this(null);
    }

    public OnlyExtraFilter(E extraValue){
        this.extraValue = extraValue;
    }


    @Override
    public boolean filter(Key<?> value) {
        return true;
    }

    @Override
    public boolean filter(E extraValue, Key<?> value) {
        if(this.extraValue != null){
            return filter(value) && this.extraValue.equals(extraValue);
        }
        return filter(value);
    }
}
