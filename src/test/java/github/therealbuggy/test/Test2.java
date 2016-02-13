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

import github.therealbuggy.configurator.AbstractBackEnd;
import github.therealbuggy.configurator.Configurator;
import github.therealbuggy.configurator.data.ExtraData;
import github.therealbuggy.configurator.IConfigurator;
import github.therealbuggy.configurator.key.Key;
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
        IConfigurator<Keys> configurator = new Configurator<Keys>(new AbstractBackEnd() {

            @Override
            public boolean pathExists(String path) {
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
            public Map<String, Object> getSectionsOnPath(String path) {
                return null;
            }

            @Override
            public Map<String, Object> getAllOnPath(String path) {
                return null;
            }

            @Override
            public ExtraData extraData() {
                return null;
            }

            @Override
            public void save() {

            }
        }){

        };

        configurator.setSectionAlias(Keys.MAIN_KEY, "Magination");
        configurator.setSectionAlias(Keys.FKEY, "Cooldown", In.path(Keys.MAIN_KEY), ValueTypes.INT_TYPE.setValue(7).build(), DefaultTranslators.getIntTranslator(configurator));
        Key<?> key = configurator.getValue(Keys.FKEY, In.path(Keys.MAIN_KEY));
        In<Keys> in = In.main();
        for(Key<?> kei : configurator.getSections(in)){
            System.out.println("Kei: "+ kei.getPath());
        }

        System.out.println("Key: "+key.getValue().getValue());
        /*
        Configurator configurator = new Configurator();
        configurator.setSectionAlias("MAIN", "uplg");
        configurator.setSectionAlias("MESSAGER_SECTION", "messager", SuperSection.getSuperSection("MAIN"));
        configurator.setSectionAlias("TIME", "interval", SuperSection.getSuperSection("MESSAGER_SECTION"), ValueTypes.INT);
        ValueTypes.INT.translate(new UnknownValueHolder("9+7"));
        configurator.getValue("TIME", In("MAIN", "MESSAGER_SECTION"));
        */
                //Key<?> key = configurator.getValue("TIME", path("MAIN", "MESSAGER_SECTION"));
                //key.getValue(Integer.class);
        //System.out.println(Reflection.tryTranslate(Integer.class, ).getClass());
        //System.out.println(Reflection.tryTranslate(Carl.class, new Dirty()).getClass());
    }
}
