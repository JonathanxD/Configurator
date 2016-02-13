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

import java.util.List;
import java.util.Map;

import github.therealbuggy.configurator.Configurator;
import github.therealbuggy.configurator.IConfigurator;
import github.therealbuggy.configurator.data.ExtraData;
import github.therealbuggy.configurator.utils.Reference;

public class ReferenceTest {

    public static void main(String[] args) {
        // Creating a List<Map<String, Integer>>

        Reference reference = Reference.referenceTo()
                .a(List.class)
                .of(
                        Reference.referenceTo()
                        .a(Map.class)
                        .of(String.class, Integer.class)
                )
                .build();
        System.out.println("Reference to style: "+reference);

        // Creating a Map<String, List<Integer>>

        Reference reference2 = Reference.referenceTo()
                .a(Map.class)
                .of(
                        Reference.referenceTo()
                                .a(String.class),
                        Reference.referenceTo()
                        .a(List.class)
                        .of(Integer.class)
                ).build();
        System.out.println("Reference2 to style: "+reference2);

        // Map<String, Map<List<Integer>, String>>
        // Map< 'String', 'Map<List<Integer>, String>' >
        // Map<String,
        //     Map<
        //         List<Integer>,
        //         String
        //        >
        //    >

        Reference reference3 = Reference.to()
                .a(Map.class)
                .of(String.class)
                .and(
                        Reference.to()
                        .a(Map.class)
                        .of(
                                Reference.referenceTo()
                                .a(List.class)
                                .of(Integer.class)
                        )
                        .and(String.class)
                ).build();
        System.out.println("Reference3 to style: "+reference3);

        IConfigurator<Integer> idConfigurator = new Configurator<>(new MyBackEndExt());

        ExtraData extraData = idConfigurator.getBackEndIConfigurator().extraData();

        System.out.println("Data PT: "+extraData.findData(Portuguese.class));
        System.out.println("MyData: "+extraData.getData(Portuguese.class));
        System.out.println("MyData: "+extraData.findData(MyData.class));
        System.out.println("MyData: "+extraData.getData(MyData.class));

        System.out.println("MyData2: "+extraData.getData(MyData2.class));

    }
}
