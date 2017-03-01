package github.therealbuggy.configurator.setter;

import github.therealbuggy.configurator.IConfigurator;
import github.therealbuggy.configurator.types.Type;

/**
 * Created by jonathan on 21/06/16.
 */
public interface ValueSetterImpl {
    void setValueToPath(String path, Type<?> valueType);

    IConfigurator<?> getConfigurator();
}
