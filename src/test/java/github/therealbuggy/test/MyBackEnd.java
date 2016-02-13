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
package github.therealbuggy.test;

import java.util.HashMap;
import java.util.Map;

import github.therealbuggy.configurator.AbstractBackEnd;
import github.therealbuggy.configurator.data.DataProvider;
import github.therealbuggy.configurator.types.Type;

/**
 * Created by jonathan on 13/02/16.
 */
@DataProvider({Portuguese.class, MyData.class})
public class MyBackEnd extends AbstractBackEnd {

    private final HashMap<String, Object> map = new HashMap<>();

    public MyBackEnd() {

    }

    @Override
    public void setValueToPath(String path, Type<?> valueType) {
        if (valueType != null) {

            if (valueType.isDefaultValuePresent()) {
                map.put(path, valueType);
            }
        } else {
            map.put(path, null);
        }
    }

    @Override
    public boolean pathExists(String path) {
        return map.containsKey(path);
    }

    @Override
    public Object getValueFromPath(String path) {
        return map.get(path);
    }

    @Override
    public String getValueFromPathAsString(String path) {
        return map.get(path).toString();
    }

    @Override
    public Map<String, Object> getValuesOnPath(String path) {
        return map;
    }

    @Override
    public Map<String, Object> getSectionsOnPath(String path) {
        return map;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> getAllOnPath(String path) {
        return map;
    }

    @Override
    public void save() {}
}
