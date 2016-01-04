package github.therealbuggy.configurator.key;

import github.therealbuggy.configurator.sections.Section;

/**
 * Created by jonathan on 02/01/16.
 */
public class KeyUtil {
    public static <T> boolean isSection(Key<T> key) {
        return key instanceof Section;
    }

    public static <T> boolean isNullKey(Key<T> key) {
        return key instanceof NullKey;
    }

    public static <T> boolean isKeyImpl(Key<T> key) {
        return key instanceof KeyImpl;
    }

    public static <T> boolean isEmptyKey(Key<T> key) {
        return key.equals(KeyImpl.empty());
    }
}
