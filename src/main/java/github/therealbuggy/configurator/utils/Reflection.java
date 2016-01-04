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
