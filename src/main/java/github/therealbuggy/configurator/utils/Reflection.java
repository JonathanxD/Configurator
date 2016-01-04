package github.therealbuggy.configurator.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Created by jonathan on 02/01/16.
 */
public class Reflection {
    /**
     * Try to translate via reflection using parse* method
     * @param <T>
     * @return
     */
    public static <T> T tryTranslate(Class<T> tClass, Object value) {
        return discovery(tClass, value, tClass.getDeclaredMethods());
    }

    private static <T> T discovery(Class<T> tClass, Object value, Method[] methods){
        for(Method method : methods) {
            if(Modifier.isStatic(method.getModifiers())) {
                if (method.getName().startsWith("parse")
                        || method.getName().startsWith("valueOf")) {
                    for (Class<?> type : method.getParameterTypes()) {
                        if (type.isAssignableFrom(value.getClass())) {
                            try {
                                Object invokeResult = method.invoke(null, value);
                                if(invokeResult != null){
                                    System.out.println(invokeResult);
                                    return (T) invokeResult;
                                }
                            } catch (IllegalAccessException | InvocationTargetException ignored) {}
                        }
                    }
                }
            }
        }

        return null;
    }
}
