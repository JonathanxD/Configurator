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

import github.therealbuggy.configurator.locale.ILocale;

/**
 * Created by jonathan on 05/01/16.
 */
public class Portuguese implements ILocale<String, Portuguese.ID> {

    public enum ID {
        DARK_BLUE
    }

    @Override
    public ID getIdOf(String value) {
        if(value.equalsIgnoreCase("%DARK_BLUE%|%DBLUE%")){
            return ID.DARK_BLUE;
        }
        return null;
    }

    @Override
    public String translate(ID id) {
        if(id.equals(ID.DARK_BLUE)) {
            return "%AZUL_ESCURO%|%AZULE%";
        }
        return null;
    }
}
