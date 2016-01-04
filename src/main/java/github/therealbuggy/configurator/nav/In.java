package github.therealbuggy.configurator.nav;

import java.lang.reflect.Array;

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

    @SuppressWarnings("unchecked")
    public In<T> back(){

        if(path.length - 1 == 0 || isMain()){
            return main();
        }

        T[] newArray = (T[]) new Object[path.length-1];
        System.arraycopy(path, 0, newArray, 0, path.length-1);

        return In.path(newArray);
    }

    public boolean isMain() {
        return this == MAIN || path.length == 0;
    }

    public static <T> In<T> main() {
        return MAIN;
    }


}
