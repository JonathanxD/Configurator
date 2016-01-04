package github.therealbuggy.configurator;


import java.util.Collection;

import github.therealbuggy.configurator.types.Type;

/**
 * Created by jonathan on 01/01/16.
 */
public interface BackEndIConfigurator {
    /**
     * Create new Section
     * @param section
     */
    void createSection(String path);

    /**
     * Define new value
     * @param path
     */
    void setValueToPath(String path, Type<?> valueType);

    boolean valueExists(String path);

    /**
     * Get value in the PATH
     * @param path Path to value
     * @return Value
     */
    Object getValueFromPath(String path);

    /**
     * Get value IN Path as {@link String}
     * @param path Path to value
     * @return The value as {@link String}
     */
    String getValueFromPathAsString(String path);

    /**
     * Get all values in determinate path
     * @param path Path to values
     * @return Values
     */
    Collection<Object> getValuesOnPath(String path);

    /**
     * Get all paths of sections in determinate path
     * @param path Path to Sections
     * @return Sections path
     */
    Collection<String> getSectionsOnPath(String path);

}
