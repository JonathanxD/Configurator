/*
 * 	Configurator - Easy way to manage configurations (for Bukkit)
 *     Copyright (C) 2016 TheRealBuggy/JonathanxD (https://github.com/JonathanxD/) <jonathan.scripter@programmer.net>
 *
 * 	GNU GPLv3
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Affero General Public License as published
 *     by the Free Software Foundation.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Affero General Public License for more details.
 *
 *     You should have received a copy of the GNU Affero General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package github.therealbuggy.configurator.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Reflection {
    /**
     * Try to translate via reflection using parse* method
     *
     * @param <T> Type
     * @return Translated value
     */
    public static <T> T tryTranslate(Class<T> tClass, Object value) {
        return discovery(tClass, value, tClass.getDeclaredMethods());
    }

    @SuppressWarnings("unchecked")
    private static <T> T discovery(Class<T> tClass, Object value, Method[] methods) {
        for (Method method : methods) {
            if (Modifier.isStatic(method.getModifiers())) {
                if (method.getName().startsWith("parse")
                        || method.getName().startsWith("valueOf")) {
                    for (Class<?> type : method.getParameterTypes()) {
                        if (type.isAssignableFrom(value.getClass())) {
                            try {
                                Object invokeResult = method.invoke(null, value);
                                if (invokeResult != null) {
                                    System.out.println(invokeResult);
                                    return (T) invokeResult;
                                }
                            } catch (IllegalAccessException | InvocationTargetException ignored) {
                            }
                        }
                    }
                }
            }
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public static <T> T tryClone(T objectToClone) {
        Class<?> classToClone = objectToClone.getClass();
        try {
            Method method = classToClone.getMethod("clone");
            if (Modifier.isPublic(method.getModifiers())) {
                return (T) method.invoke(objectToClone);
            }
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException ignored) {
        }

        return objectToClone;
    }

    @SuppressWarnings("unchecked")
    public static <T> T findStaticField(Class<?> classToFind, String fieldName) {
        try {
            Field field = classToFind.getDeclaredField(fieldName);
            return (T) field.get(null);
        } catch (Exception ignored) {
            return null;
        }
    }

    /**
     * Recursive get annotation
     * @param aClass Class with annotations (analyze subclasses and interfaces)
     * @param annotationClass Annotation
     * @param <T> Type of Annotation
     * @return Annotation Array
     */
    @SuppressWarnings("unchecked")
    public static <T extends Annotation> T[] getAnnotation(Class<?> aClass, Class<T> annotationClass) {
        List<T> list = new ArrayList<>();

        Class<?> superClass = aClass.getSuperclass();
        if(superClass != null) {

            T[] annotations = getAnnotation(superClass, annotationClass);

            if(annotations.length > 0) {
                addAll(list, annotations);
            }
        }

        Class<?>[] interfaces = aClass.getInterfaces();

        if(interfaces != null && interfaces.length > 0) {
            for(Class<?> sub : aClass.getInterfaces()) {
                T[] annotations = getAnnotation(sub, annotationClass);

                if(annotations.length > 0) {
                    addAll(list, annotations);
                }
            }
        }


        addAll(list, aClass.getDeclaredAnnotationsByType(annotationClass));

        T[] t = (T[]) Array.newInstance(annotationClass, list.size());

        return list.toArray(t);
    }

    private static <T extends Annotation> void addAll(List<T> list, T[] array) {
        if(array == null || array.length <= 0)
            return;

        for(T t : array) {
            if(!list.contains(t))
                list.add(t);
        }
    }
}
