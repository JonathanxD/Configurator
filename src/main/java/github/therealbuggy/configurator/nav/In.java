package github.therealbuggy.configurator.nav;

/**
 * Created by jonathan on 02/01/16.
 */
public class In<T> {
    @Deprecated
    public static final In MAIN = new In();

    private final T[] path;

    In(T... path){
        this.path = path;
    }

    public static <T> In<T> path(T... path){
        return new In<>(path);
    }

    public T[] getPath() {
        return path;
    }

    public boolean isMain() {
        return this == MAIN || path.length == 0;
    }

    public static <T> In<T> main() {
        return MAIN;
    }

}
