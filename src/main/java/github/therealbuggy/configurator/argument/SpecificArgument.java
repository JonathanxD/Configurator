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
package github.therealbuggy.configurator.argument;

import java.util.HashMap;
import java.util.Map;

import github.therealbuggy.configurator.exceptions.ClassNotAllowedException;
import github.therealbuggy.configurator.exceptions.NotEnoughArgumentsToApply;

/**
 * Created by jonathan on 05/01/16.
 */
public class SpecificArgument {
    private final Map<String, Class<?>> argumentMap = new HashMap<>();
    private String[] keysBuffer;
    private Class<?>[] valuesBuffer;


    SpecificArgument(Object... keysAndValues) {
        if(keysAndValues.length % 2 == 0){
            boolean isKey = true;
            Object key = null;
            for(Object object : keysAndValues) {
                if(isKey) {
                    if(!(object instanceof String))
                        throw new ClassNotAllowedException("For keys, only Strings");
                    key = object;
                }else{
                    if(!(object instanceof Class<?>))
                        throw new ClassNotAllowedException("For values, only Classes");

                    if(key == null) throw new NullPointerException("Null key");
                    argumentMap.put((String) key, (Class<?>) object);
                    key = null;
                }

                isKey = !isKey;
            }
        }else{
            throw new NotEnoughArgumentsToApply("Cannot apply [SpecificArgument]!");
        }
    }

    SpecificArgument(String[] keys, Class<?>[] values){
        keysBuffer = keys;
        valuesBuffer = values;
        finish();
    }

    SpecificArgument() {}

    public void validate() {
        if(argumentMap.isEmpty()) {
            throw new NullPointerException("Empty argument map");
        }
    }

    private void finish() {
        if(keysBuffer.length == valuesBuffer.length) {
            for(int x = 0; x < keysBuffer.length; ++x) {
                argumentMap.put(keysBuffer[x], valuesBuffer[x+1]);
            }
        }else{
            throw new NotEnoughArgumentsToApply("Cannot apply [SpecificArgument]!");
        }
        keysBuffer = new String[0];
        valuesBuffer = new Class<?>[0];
    }

    public SpecificArgument keys(String... keys) {
        keysBuffer = keys;
        if(valuesBuffer.length == keysBuffer.length){
            finish();
        }

        return this;
    }

    public SpecificArgument values(Class<?>... values) {
        valuesBuffer = values;
        if(valuesBuffer.length == keysBuffer.length){
            finish();
        }
        return this;
    }

    public static SpecificArgument of(Object... keysAndValues) {
        return new SpecificArgument(keysAndValues);
    }

    public static SpecificArgument of(String[] keys, Class<?>[] values) {
        return new SpecificArgument(keys, values);
    }

    public static SpecificArgument ofEmpty() {
        return new SpecificArgument();
    }

    Map<String, Class<?>> getArgumentMap() {
        return argumentMap;
    }
}
