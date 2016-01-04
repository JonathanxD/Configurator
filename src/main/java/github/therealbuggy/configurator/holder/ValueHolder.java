package github.therealbuggy.configurator.holder;

/**
 * Created by jonathan on 02/01/16.
 */
public class ValueHolder<T> {
    private static final ValueHolder EMPTY = new ValueHolder<>(null);

    private final T value;
    public ValueHolder(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public boolean isEmpty() {
        return this == empty() || this.value == null;
    }

    public static <T> ValueHolder<T> empty() {
        return EMPTY;
    }
}
