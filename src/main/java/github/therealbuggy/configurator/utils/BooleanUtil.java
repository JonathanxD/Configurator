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
package github.therealbuggy.configurator.utils;

import java.util.regex.Pattern;

/**
  * Created by jonathan on 20/12/15.
  */
public class BooleanUtil {

    private static final Pattern truePattern = Pattern.compile("(?i)(s|y|t|sim|yes|true)");
    private static final Pattern falsePattern = Pattern.compile("(?i)(n|f|nao|no|false)");

    public static boolean booleanFromString(String stringRepresentation) {
        if(truePattern.matcher(stringRepresentation).matches())return true;
        if(falsePattern.matcher(stringRepresentation).matches())return false;
        throw new RuntimeException("Cannot get value from string representation: "+stringRepresentation);
    }
}
