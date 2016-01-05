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
package github.therealbuggy.configurator.types;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by jonathan on 04/01/16.
 */
public class TypeBuilder<T> {

    private final Class<? extends Type> typeClass;
    private Constructor<?> constructor;
    private T defaultValue;

    public TypeBuilder(Class<? extends Type> typeClass) {
        this.typeClass = typeClass;
    }

    @SuppressWarnings("unchecked")
    public Type<T> build() {
        if (constructor == null) {
            processConstructor(defaultValue);
        }

        Object construct = null;

        try {
            if(constructor.getParameterTypes().length > 0){
                construct = constructor.newInstance(defaultValue);
            }else{
                construct = constructor.newInstance();
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException ignored) {
        }

        if(construct == null){
            return null;
        }

        return (Type<T>) construct;
    }

    public TypeBuilder<T> setValue(T defaultValue) {
        acceptDefaultValue(defaultValue);
        return this;
    }

    public boolean acceptDefaultValue(T defaultValue) {
        processConstructor(defaultValue);

        if(this.constructor.getParameterTypes().length > 0){
            this.defaultValue = defaultValue;
            return true;
        }else{
            return false;
        }
    }


    private void processConstructor(T defaultValue) {
        for (Constructor<?> constructor : typeClass.getDeclaredConstructors()) {
            constructor.setAccessible(true);
            if(defaultValue != null){
                for (Class<?> type : constructor.getParameterTypes()) {
                    if (type.isAssignableFrom(defaultValue.getClass())) {
                        this.constructor = constructor;
                        return;
                    }
                }
            }
            if(constructor.getParameterTypes().length == 0){
                this.constructor = constructor;
                return;
            }
        }
        try {
            this.constructor = typeClass.getConstructor();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            this.constructor = null;
        }
    }

    public static class TypeHelper {
        public static String typeToString(Type<?> type) {
            return type.getClass().getSimpleName()+"["
                    +"defaultValuePresent: "+type.isDefaultValuePresent()
                    +(type.isDefaultValuePresent() ? ", defaultValue: "+String.valueOf(type.defaultValue()) : "")
                    +"]";
        }
    }
}
