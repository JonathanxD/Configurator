package github.therealbuggy.configurator.child;

/**
 * Created by jonathan on 02/01/16.
 */

/**
 * ** Readability Improvement **
 */
public class SuperSection {

    private final String sectionName;

    SuperSection(String sectionName) {
        this.sectionName = sectionName;
    }

    public static SuperSection getSuperSection(String sectionName) {
        return new SuperSection(sectionName);
    }

    public String getSectionName() {
        return sectionName;
    }
}
