package github.therealbuggy.configurator.mapconfigurator.filter;

/**
 * Created by jonathan on 04/01/16.
 */
public interface Filter<E> {
    boolean filter(E value);
}
