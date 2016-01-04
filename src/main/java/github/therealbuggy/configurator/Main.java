package github.therealbuggy.configurator;

import java.util.Collection;

import github.therealbuggy.configurator.key.Key;
import github.therealbuggy.configurator.key.KeyUtil;
import github.therealbuggy.configurator.nav.In;
import github.therealbuggy.configurator.translator.statics.DefaultTranslators;
import github.therealbuggy.configurator.types.Type;
import github.therealbuggy.configurator.types.ValueTypes;

public class Main {

    public static void main(String[] args) {
        IConfigurator<String> configurator = new MapConfigurator<String>(true, new BackEndIConfigurator() {
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
