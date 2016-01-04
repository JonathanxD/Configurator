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

import java.util.Collection;
import java.util.Map;

import github.therealbuggy.configurator.BackEndIConfigurator;
import github.therealbuggy.configurator.Configurator;
import github.therealbuggy.configurator.IConfigurator;
import github.therealbuggy.configurator.MapConfigurator;
import github.therealbuggy.configurator.key.Key;
import github.therealbuggy.configurator.key.KeyUtil;
import github.therealbuggy.configurator.nav.In;
import github.therealbuggy.configurator.translator.statics.DefaultTranslators;
import github.therealbuggy.configurator.types.Type;
import github.therealbuggy.configurator.types.ValueTypes;

public class Test2 {

    enum Keys {
        MAIN_KEY,
        FKEY
    }

    public static void main(String[] args) {
        IConfigurator<Keys> configurator = new Configurator<Keys>(true, new BackEndIConfigurator() {

            @Override
            public boolean valueExists(String path) {
                return false;
            }

            @Override
            public void setValueToPath(String path, Type<?> valueType) {
                System.out.println("Setting value: "+valueType+" to path "+path);
            }

            @Override
            public Object getValueFromPath(String path) {
                System.out.println("Tentou obter do path: "+path);
                if(path.equals("in.an")){
                    return 7;
                }
                return "($in.an)*4";
            }

            @Override
            public String getValueFromPathAsString(String path) {
                return String.valueOf(getValueFromPath(path));
            }

            @Override
            public Map<String ,Object> getValuesOnPath(String path) {
                return null;
            }

            @Override
            public Collection<String> getSectionsOnPath(String path) {
                return null;
            }
        }){

        };

        configurator.tagSection(Keys.MAIN_KEY, "Magination");
        configurator.tagSection(Keys.FKEY, "Cooldown", In.path(Keys.MAIN_KEY), ValueTypes.INT_TYPE.setValue(7).build());
        Key<?> key = configurator.getValue(Keys.FKEY, In.path(Keys.MAIN_KEY));
        In<Keys> in = In.main();
        for(Key<?> kei : configurator.getSections(in)){
            System.out.println("Kei: "+ kei.getPath());
        }

        System.out.println("Key: "+key.getValue(DefaultTranslators.getIntTranslator(configurator)).getValue());
        /*
        Configurator configurator = new Configurator();
        configurator.tagSection("MAIN", "uplg");
        configurator.tagSection("MESSAGER_SECTION", "messager", SuperSection.getSuperSection("MAIN"));
        configurator.tagSection("TIME", "interval", SuperSection.getSuperSection("MESSAGER_SECTION"), ValueTypes.INT);
        ValueTypes.INT.translate(new UnknownValueHolder("9+7"));
        configurator.getValue("TIME", In("MAIN", "MESSAGER_SECTION"));
        */
                //Key<?> key = configurator.getValue("TIME", path("MAIN", "MESSAGER_SECTION"));
                //key.getValue(Integer.class);
        //System.out.println(Reflection.tryTranslate(Integer.class, ).getClass());
        //System.out.println(Reflection.tryTranslate(Carl.class, new Dirty()).getClass());
    }
}
