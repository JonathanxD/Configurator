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

import java.awt.*;

import github.therealbuggy.configurator.locale.ILocale;
import github.therealbuggy.configurator.modifiers.IModifier;
import github.therealbuggy.configurator.locale.LocaleList;

/**
 * Sample color modifier
 */
public class ColorModifier implements IModifier<String> {

    private final LocaleList<String> locale;

    ColorModifier(LocaleList<String> locale){
        this.locale = locale;
    }

    @Override
    public String modify(String value) {
        String toReplace = locale.translate("%DARK_GRAY%|%DGRAY%");
        value = value.replaceAll(toReplace, String.valueOf(Color.DARK_GRAY));
        return value.replaceAll("%DARK_BLUE%|%DBLUE%", String.valueOf(Color.DARK_GRAY));
    }

    @Override
    public LocaleList<String> getLocale() {
        return locale;
    }

    @Override
    public ILocale<String, ?> getDefaultLocale() {
        return null;
    }
}
