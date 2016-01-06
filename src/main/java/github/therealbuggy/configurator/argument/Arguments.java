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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import github.therealbuggy.configurator.exceptions.NotEnoughArgumentsToApply;
import github.therealbuggy.configurator.utils.Reflection;

/**
 * Created by jonathan on 05/01/16.
 */
public class Arguments {
    private final Map<String, Class<?>> argumentMap = new HashMap<>();

    public Arguments set(String regex, Class<?> type){
        argumentMap.put(regex, type);
        return this;
    }

    public Arguments remove(String regex) {
        argumentMap.remove(regex);
        return this;
    }

    public Arguments remove(Class<?> type) {
        Iterator<Map.Entry<String, Class<?>>> argsIterator = argumentMap.entrySet().iterator();
        while(argsIterator.hasNext()) {
            Map.Entry<String, Class<?>> entry = argsIterator.next();
            if(entry.getValue() == type){
                argsIterator.remove();
            }
        }
        return this;
    }

    public Applier apply(String input) {
        return new Applier(input, argumentMap);
    }

    public static class Applier {
        private final String input;
        private final Map<String, Class<?>> argMap;

        Applier(String input, Map<String, Class<?>> argMap) {
            this.input = input;
            this.argMap = Collections.unmodifiableMap(argMap);
        }

        public final String applyOrdered(Object... inputs){
            String inputClone = Reflection.tryClone(input);
            if(argMap.size() != inputs.length) throw new NotEnoughArgumentsToApply("Cannot apply two different arrays size!");

            List<Map.Entry<String, Class<?>>> entries = new LinkedList<>(argMap.entrySet());
            for(int x = 0; x < entries.size(); ++x) {
                Map.Entry<String, Class<?>> entry = entries.get(x);
                Object cloneInput = Reflection.tryClone(inputs[x]);
                inputClone = inputClone.replaceAll(entry.getKey(), String.valueOf(cloneInput));
            }
            return inputClone;
        }

        public final String applyTyped(Object... inputs){
            String inputClone = Reflection.tryClone(input);
            List<Object> objectList = new ArrayList<>(Arrays.asList(inputs));

            for(Map.Entry<String, Class<?>> entry : argMap.entrySet()) {
                Iterator<Object> objectIterator = objectList.iterator();

                while(objectIterator.hasNext()){
                    Object next = objectIterator.next();

                    if(next.getClass() == entry.getValue()) {
                        inputClone = inputClone.replaceAll(entry.getKey(), String.valueOf(next));
                        objectIterator.remove();
                    }

                }

            }
            return inputClone;
        }

        public final String applySpecific(SpecificArgument specificArgument){
            if(argMap.size() != specificArgument.getArgumentMap().size()) throw new NotEnoughArgumentsToApply("Cannot apply two different arrays size!");
            String inputClone = Reflection.tryClone(input);

            Map<String, Class<?>> mapSpecific = specificArgument.getArgumentMap();

            for(Map.Entry<String, Class<?>> entry : argMap.entrySet()) {
                Iterator<Map.Entry<String, Class<?>>> mapSpecificEntries = mapSpecific.entrySet().iterator();

                while(mapSpecificEntries.hasNext()){
                    Map.Entry<String, Class<?>> next = mapSpecificEntries.next();

                    if(next.getValue() == entry.getValue()){
                        inputClone = inputClone.replaceAll(entry.getKey(), String.valueOf(next.getKey()));
                        mapSpecificEntries.remove();
                        break;
                    }
                }
            }

            return inputClone;
        }
    }

}
