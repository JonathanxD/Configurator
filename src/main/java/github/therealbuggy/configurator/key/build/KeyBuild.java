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
package github.therealbuggy.configurator.key.build;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import github.therealbuggy.configurator.BackEndIConfigurator;
import github.therealbuggy.configurator.key.Key;
import github.therealbuggy.configurator.key.KeyImpl;
import github.therealbuggy.configurator.sections.Section;
import github.therealbuggy.configurator.translator.Translator;
import github.therealbuggy.configurator.types.Type;

/**
 * Created by jonathan on 29/01/16.
 */
public class KeyBuild {

    public static <T> Key<T> buildFromPath(String path, BuildType buildType, Translator<T> translator, Type<T> type, BackEndIConfigurator iConfigurator) {

        List<String> list = new ArrayList<>(Arrays.asList(path.split("\\.")));
        Iterator<String> iterator = list.iterator();

        Key<T> key = null;
        Section<T> lastSection = null;

        boolean section = true;

        while (iterator.hasNext()) {
            String text = iterator.next();
            if (!iterator.hasNext())
                section = false;

            if (section) {
                lastSection = new Section<>(text, lastSection, iConfigurator);
            } else {
                if (buildType == BuildType.KEY_IMPL) {
                    key = new KeyImpl<>(text, path, lastSection, type, translator, null, iConfigurator);
                } else if (buildType == BuildType.SECTION) {
                    key = new Section<>(text, lastSection, iConfigurator);
                }

            }
        }

        return key;
    }

    public static <T> Key<T> buildKeyFromPath(String path, Translator<T> translator, Type<T> type, BackEndIConfigurator iConfigurator) {
        return buildFromPath(path, BuildType.KEY_IMPL, translator, type, iConfigurator);
    }

    public static <T> Key<T> buildSectionFromPath(String path, BackEndIConfigurator iConfigurator) {
        return buildFromPath(path, BuildType.SECTION, null, null, iConfigurator);
    }

    public enum BuildType {
        KEY_IMPL, SECTION
    }

}
