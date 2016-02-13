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

import github.therealbuggy.configurator.IConfigurator;

/**
 * Created by jonathan on 13/02/16.
 */
public class MyData {

    private final IConfigurator<?> configurator;
    private final Portuguese portuguese;

    public MyData(IConfigurator<?> configurator, Portuguese portuguese) {
        this.configurator = configurator;
        this.portuguese = portuguese;
    }

    public IConfigurator<?> getConfigurator() {
        return configurator;
    }

    public Portuguese getPortuguese() {
        return portuguese;
    }

    @Override
    public String toString() {
        return "MyData[configurator: "+getConfigurator()+", portuguese: "+getPortuguese()+"]";
    }
}
