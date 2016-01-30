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
package github.therealbuggy.configurator.sections;

import github.therealbuggy.configurator.BackEndIConfigurator;

public class Section<E> extends SectionNullKeyImpl<E> {
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

    @Override
    public String getName() {
        return name;
    }
}
