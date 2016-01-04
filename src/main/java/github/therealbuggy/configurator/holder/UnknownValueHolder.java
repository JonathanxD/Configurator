package github.therealbuggy.configurator.holder;

import github.therealbuggy.configurator.utils.BooleanUtil;
import github.therealbuggy.configurator.utils.Reflection;

/**
 * Created by jonathan on 02/01/16.
 */
public class UnknownValueHolder extends ValueHolder<Object> {
    private static final UnknownValueHolder EMPTY = new UnknownValueHolder(null);

    public UnknownValueHolder(Object value) {
        super(value);
    }

    public <T> T getAs(Class<T> tClass) {
        return (T) getValue();
    }

    public String getAsString() {
        /**
         * Try to translate
         */
        String i = translate(String.class);

        if (i != null) {
            return i;
        }

        return getAs(String.class);
    }

    public int getAsInt() {
        /**
         * Try to translate
         */
        Integer i = translate(Integer.class);

        if (i != null) {
            return i;
        }

        return getAs(Integer.class);
    }

    public double getAsDouble() {
        /**
         * Try to translate
         */
        Double i = translate(Double.class);

        if (i != null) {
            return i;
        }

        return getAs(Double.class);
    }

    public float getAsFloat() {
        /**
         * Try to translate
         */
        Float i = translate(Float.class);

        if (i != null) {
            return i;
        }

        return getAs(Float.class);
    }

    public byte getAsByte() {
        /**
         * Try to translate
         */
        Byte i = translate(Byte.class);

        if (i != null) {
            return i;
        }

        return getAs(Byte.class);
    }

    public long getAsLong() {
        /**
         * Try to translate
         */
        Long i = translate(Long.class);

        if (i != null) {
            return i;
        }

        return getAs(Long.class);
    }

    public short getAsShort() {
        /**
         * Try to translate
         */
        Short i = translate(Short.class);

        if (i != null) {
            return i;
        }

        return getAs(Short.class);
    }

    public boolean getAsBoolean() {
        /**
         * Try to translate
         */
        Boolean i = translate(Boolean.class);

        if (i != null) {
            return i;
        }

        /**
         * Use BooleanUtil: Try to translate booleans from string
         */
        try{
            BooleanUtil.booleanFromString(String.valueOf(getValue()));
        }catch(RuntimeException ignored){}

        return getAs(Boolean.class);
    }

    public char getAsChar() {
        /**
         * Try to translate
         */
        Character i = translate(Character.class);

        if (i != null) {
            return i;
        }

        return getAs(Character.class);
    }

    /**
     * Try to translate via reflection using parse* method
     * @param <T>
     * @return
     */
    private <T> T translate(Class<T> tClass) {
        return Reflection.tryTranslate(tClass, getValue());
    }

    @Override
    public boolean isEmpty() {
        return (super.isEmpty() ? super.isEmpty() : this == empty());
    }

    public static UnknownValueHolder empty(){
        return EMPTY;
    }
}
