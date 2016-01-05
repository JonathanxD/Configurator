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
package github.therealbuggy.configurator.nav;

/**
 * Created by jonathan on 02/01/16.
 */
public class In<T> {
    @SuppressWarnings("unchecked")
    private static final In MAIN = new In();

    private final T[] path;

    @SafeVarargs
    In(T... path){
        this.path = path;
    }

    @SafeVarargs
    public static <T> In<T> path(T... path){
        return new In<>(path);
    }

    public T[] getPath() {
        return path;
    }

    @SuppressWarnings("unchecked")
    public In<T> back(){

        if(path.length - 1 == 0 || isMain()){
            return main();
        }

        T[] newArray = (T[]) new Object[path.length-1];
        System.arraycopy(path, 0, newArray, 0, path.length-1);

        return In.path(newArray);
    }

    public boolean isMain() {
        return this == MAIN || path.length == 0;
    }

    @SuppressWarnings("unchecked")
    public static <T> In<T> main() {
        return MAIN;
    }


}
