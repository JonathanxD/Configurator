package github.therealbuggy.configurator.sections;

import github.therealbuggy.configurator.BackEndIConfigurator;

/**
 * Created by jonathan on 02/01/16.
 */
public class Section<E> extends SectionNullKeyImpl {
    private final String name;
    private final Section superSection;

    public Section(String name) {
        this(name, null);
    }

    public Section(String name, Section superSection) {
        this(name, superSection, null);
    }

    public Section(String name, Section superSection, BackEndIConfigurator iConfigurator) {
        super(superSection, iConfigurator);
        this.name = name;
        this.superSection = superSection;
    }

    @Override
    public Section section() {
        return superSection;
    }

    @Override
    public String getPath() {
        return (superSection != null ? superSection.getPath()+"." : "") + this.name;
    }
}
