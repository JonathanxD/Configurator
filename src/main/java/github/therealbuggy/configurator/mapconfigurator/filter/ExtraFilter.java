package github.therealbuggy.configurator.mapconfigurator.filter;

/**
 * Created by jonathan on 04/01/16.
 */
public interface ExtraFilter<T, E> extends Filter<E> {
    boolean filter(T extraValue, E value);
}
