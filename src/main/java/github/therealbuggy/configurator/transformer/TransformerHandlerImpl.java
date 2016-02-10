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
package github.therealbuggy.configurator.transformer;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import github.therealbuggy.configurator.key.Key;

/**
 * Created by jonathan on 10/02/16.
 */
public class TransformerHandlerImpl implements ITransformerHandler {

    private final Set<Transformer<?>> transformers = new HashSet<>();

    @SuppressWarnings("unchecked")
    @Override
    public <T> Optional<TransformedObject<T>> transform(Key<?> sectionToTransform) {

        for (Transformer<?> transformer : transformers) {
            try {
                Optional<T> transform = (Optional<T>) transformer.transformSection(sectionToTransform);
                if (transform.isPresent()) {

                    Class<? extends Transformer> transformerClass = transformer.getClass();
                    return Optional.of(new TransformedObject<>(transform.get(), transformerClass));

                }
            } catch (Exception ignored) {
            }

        }

        return Optional.empty();
    }

    @Override
    public Collection<Transformer<?>> getTransformers() {
        return Collections.unmodifiableSet(transformers);
    }

    @Override
    public void addTransformer(Transformer<?> transformer) {
        transformers.add(transformer);
    }

    @Override
    public void removeTransformer(Transformer<?> transformer) {
        transformers.remove(transformer);
    }
}
