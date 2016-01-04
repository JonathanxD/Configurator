package github.therealbuggy.configurator;

/**
 * Created by jonathan on 04/01/16.
 */
public class Configurator<E> extends MapConfigurator<E> {

    public Configurator(@Deprecated boolean insideSection, BackEndIConfigurator backEndIConfigurator) {
        super(insideSection, backEndIConfigurator);
    }

    public Configurator(BackEndIConfigurator backEndIConfigurator) {
        super(backEndIConfigurator);
    }
}
