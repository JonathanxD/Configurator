package github.therealbuggy.configurator.types;

/**
 * Created by jonathan on 01/01/16.
 */
public class ValueTypes {
    @Deprecated
    public static final Any ANY = new Any();
    @Deprecated
    public static final Int INT = new Int();

    public static final TypeBuilder<Integer> INT_TYPE = new TypeBuilder<>(Int.class);
    public static final TypeBuilder<Integer> ANY_TYPE = new TypeBuilder<>(Any.class);
    public static final TypeBuilder<Integer> BOOL_TYPE = new TypeBuilder<>(Bool.class);

    public static Int IntType() {
        return new Int();
    }

    public static Int IntType(Integer defaultValue) {
        return new Int(defaultValue);
    }

    public static Bool BoolType() {
        return new Bool();
    }

    public static Bool BoolType(Boolean defaultValue) {
        return new Bool(defaultValue);
    }

    public static Any AnyType() {
        return new Any();
    }

    public static Any AnyType(Object defaultValue) {
        return new Any(defaultValue);
    }

}
