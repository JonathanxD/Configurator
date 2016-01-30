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
package github.therealbuggy.configurator.types;

public class ValueTypes {
    @Deprecated
    public static final Any ANY = new Any();
    @Deprecated
    public static final Int INT = new Int();

    public static final TypeBuilder<Integer> INT_TYPE = new TypeBuilder<>(Int.class);
    public static final TypeBuilder<Object> ANY_TYPE = new TypeBuilder<>(Any.class);
    public static final TypeBuilder<Boolean> BOOL_TYPE = new TypeBuilder<>(Bool.class);
    public static final TypeBuilder<String> STR_TYPE = new TypeBuilder<>(Str.class);

    public static Int IntType() {
        return new Int();
    }

    public static Int IntType(Integer defaultValue) {
        return new Int(defaultValue);
    }

    public static Bool BoolType() {
        return new Bool();
    }

    public static Bool BoolType(Boolean defaultValue) {
        return new Bool(defaultValue);
    }

    public static Any AnyType() {
        return new Any();
    }

    public static Any AnyType(Object defaultValue) {
        return new Any(defaultValue);
    }

    public static <T> List<T> ListType() {
        return new List<>();
    }

    public static <T> List<T> ListType(java.util.List<T> defaultValue) {
        return new List<>(defaultValue);
    }

    public static Str StrType() {
        return new Str();
    }

    public static Str StrType(String defaultValue) {
        return new Str(defaultValue);
    }

}
