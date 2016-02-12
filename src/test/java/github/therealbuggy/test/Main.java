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

public class Main {

    public static void main(String[] args) {
        IConfigurator<String> configurator = new Configurator<String>(new BackEndIConfigurator() {

            @Override
            public Object getValueFromPath(String path) {
                System.out.println("Tentou obter do path: " + path);
                if (path.equals("in.an")) {
                    return 7;
                }
                return "($in.an)*4";
            }

            @Override
            public String getValueFromPathAsString(String path) {
                return String.valueOf(getValueFromPath(path));
            }

            @Override
            public Map<String, Object> getValuesOnPath(String path) {
                return null;
            }

            @Override
            public void setValueToPath(String path, Type<?> valueType) {
                System.out.println("Setting value: " + valueType + " to path " + path);
            }

            @Override
            public boolean valueExists(String path) {
                return true;
            }

            @Override
            public Map<String, Object> getSectionsOnPath(String path) {
                return null;
            }

            @Override
            public Map<String, Object> getAllOnPath(String path) {
                return null;
            }
        }) {

        };

        Key<?> main = configurator.setSectionAlias("PRINCIPAL", "auto_command_plugin");
        Key<?> commands = configurator.setSectionAlias("COMANDOS", "commands", In.path("PRINCIPAL"));


        configurator.setSectionAlias("INTERVALO", "tempo", In.path("PRINCIPAL"));


        configurator.setSectionAlias("TIME", "interval", ValueTypes.IntType(), In.path("PRINCIPAL", "INTERVALO"), DefaultTranslators.getIntTranslator(configurator));


        Key<?> key = configurator.getValue("TIME", In.path("PRINCIPAL", "INTERVALO"));

        if (!KeyUtil.isEmptyKey(key)) {
            System.out.println(key.getValue());
            System.out.println(key.getValue().getValue());
        }

    }
}
