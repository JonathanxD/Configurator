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

import java.util.ArrayList;
import java.util.Map;

import github.therealbuggy.configurator.BackEndIConfigurator;
import github.therealbuggy.configurator.Configurator;
import github.therealbuggy.configurator.IConfigurator;
import github.therealbuggy.configurator.key.Key;
import github.therealbuggy.configurator.key.KeyUtil;
import github.therealbuggy.configurator.nav.In;
import github.therealbuggy.configurator.translator.statics.DefaultTranslators;
import github.therealbuggy.configurator.types.Type;
import github.therealbuggy.configurator.types.ValueTypes;
import github.therealbuggy.configurator.utils.Reflection;

public class TestReflection {

    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<>();


        ArrayList<String> clone = Reflection.tryClone(arrayList);
        System.out.println("clone == arrayList");
        System.out.println(clone == arrayList);
        
    }
}
