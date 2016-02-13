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

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * Created by jonathan on 13/02/16.
 */
public class Reference implements Comparable<Reference> {

    private final Class<?> aClass;
    private final Reference[] related;
    private final Object hold;

    Reference(Class<?> aClass, Reference[] related, Object hold) {
        this.hold = hold;
        this.aClass = Objects.requireNonNull(aClass);
        this.related = related != null ? related : new Reference[0];
    }

    @NotNull
    public static String toString(Reference reference) {

        StringBuilder sb = new StringBuilder();
        String shortName = reference.getAClass().getSimpleName();
        sb.append(shortName);

        if (reference.getRelated().length != 0) {
            sb.append("<");
            StringJoiner sj = new StringJoiner(", ");

            for (Reference loopRef : reference.getRelated()) {
                sj.add(toString(loopRef));
            }

            String processResult = sj.toString();
            sb.append(processResult);
            sb.append(">");
        }

        return sb.toString();
    }

    public static ReferenceBuilder to() {
        return referenceTo();
    }

    public static ReferenceBuilder referenceTo() {
        return new ReferenceBuilder();
    }

    public static ReferenceBuilder a(Class<?> aClass) {
        return referenceTo().a(aClass);
    }

    public static ReferenceBuilder but(Reference reference) {
        return referenceTo().a(reference.getAClass()).of(reference.getRelated());
    }

    public ReferenceBuilder but() {
        return Reference.but(this);
    }

    public Class<?> getAClass() {
        return aClass;
    }

    public Object get() {
        return hold;
    }

    public Reference[] getRelated() {
        return related;
    }

    @Override
    public String toString() {
        return toString(this);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aClass, Arrays.deepHashCode(related));
    }

    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof Reference))
            return false;

        Reference other = (Reference) obj;

        return compareTo(other) == 0;
    }

    @Override
    public int compareTo(@NotNull Reference compareTo) {

        if (getAClass() == compareTo.getAClass()) {

            if (Arrays.deepEquals(getRelated(), compareTo.getRelated())) {
                return 0;
            }

            return 1;
        }

        return -1;
    }

    /**
     * Created by jonathan on 13/02/16.
     */
    public static class ReferenceBuilder {
        private Class<?> aClass;
        private List<Reference> related = new ArrayList<>();
        private Object hold = null;

        ReferenceBuilder() {
        }

        public ReferenceBuilder a(Class<?> aClass) {
            this.aClass = aClass;
            return this;
        }

        @Optional
        public ReferenceBuilder hold(Object object) {
            this.hold = object;
            return this;
        }

        // Of
        @Optional
        public ReferenceBuilder of(List<Reference> related) {
            this.related.addAll(related);
            return this;
        }

        @Optional
        public ReferenceBuilder of(Reference... related) {
            of(Arrays.asList(related));
            return this;
        }

        @Optional
        public ReferenceBuilder of(ReferenceBuilder... builders) {

            for (ReferenceBuilder builder : builders) {
                of(builder.build());
            }
            return this;
        }

        @Optional
        public ReferenceBuilder of(Class<?>... classes) {

            List<Reference> references = new ArrayList<>();

            for (Class<?> classz : classes) {
                references.add(referenceTo().a(classz).build());
            }

            of(references);

            return this;
        }

        // AND OF
        @Optional
        public ReferenceBuilder and(List<Reference> related) {
            andCheck();
            of(related);
            return this;
        }

        @Optional
        public ReferenceBuilder and(Reference... related) {
            andCheck();
            of(related);
            return this;
        }

        @Optional
        public ReferenceBuilder and(ReferenceBuilder... builders) {
            andCheck();
            of(builders);
            return this;
        }

        @Optional
        public ReferenceBuilder and(Class<?>... classes) {
            andCheck();
            of(classes);
            return this;
        }

        private void andCheck() {
            if (related.size() == 0)
                throw new IllegalStateException("'and' cannot be used here! Usage ex: referenceTo().a(Object.class).of(String.class).and(Class.class)");
        }

        public Reference build() {
            return new Reference(aClass, related.toArray(new Reference[related.size()]), hold);
        }
    }
}
