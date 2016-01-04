package github.therealbuggy.test;

import java.util.Collection;

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

public class Main {

    public static void main(String[] args) {
        IConfigurator<String> configurator = new Configurator<String>(true, new BackEndIConfigurator() {
            @Override
            public void createSection(String section) {

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
            public Collection<Object> getValuesOnPath(String path) {
                return null;
            }

            @Override
            public void setValueToPath(String path, Type<?> valueType) {
                System.out.println("Setting value: "+valueType+" to path "+path);
            }

            @Override
            public boolean valueExists(String path) {
                return false;
            }

            @Override
            public Collection<String> getSectionsOnPath(String path) {
                return null;
            }
        }){

        };

        Key<?> main = configurator.tagSection("PRINCIPAL", "auto_command_plugin");
        Key<?> commands = configurator.tagSection("COMANDOS", "commands", In.path("PRINCIPAL"));


        configurator.tagSection("INTERVALO", "tempo", In.path("PRINCIPAL"));


        configurator.tagSection("TIME", "interval", ValueTypes.INT, In.path("PRINCIPAL", "INTERVALO"));


        Key<?> key = configurator.getValue("TIME", In.path("PRINCIPAL", "INTERVALO"));

        if(!KeyUtil.isEmptyKey(key)){
            System.out.println(key.getValue());
            System.out.println(key.getValue(DefaultTranslators.getIntTranslator(configurator)).getValue());
        }
        
    }
}
