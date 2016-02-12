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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import github.therealbuggy.configurator.IConfigurator;
import github.therealbuggy.configurator.key.Key;

/**
 * Created by jonathan on 10/02/16.
 */
public class TransformerHandlerImpl implements ITransformerHandler {

    private final List<Transformer<?>> transformers = new ArrayList<>();
    private final IConfigurator<?> configurator;

    public TransformerHandlerImpl(IConfigurator<?> configurator) {
        this.configurator = configurator;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> Optional<TransformedObject<T>> transform(Key<?> sectionToTransform) {

        for (Transformer<?> transformer : transformers) {
            try {

                Optional<T> transform = (Optional<T>) transformer.transformSection(sectionToTransform, configurator);
                if (transform.isPresent()) {

                    Class<? extends Transformer> transformerClass = transformer.getClass();
                    return Optional.of(new TransformedObject<>(transform.get(), transformerClass));

                }
            } catch (Exception ignored) {
                ignored.printStackTrace();
            }

        }

        return Optional.empty();
    }

    // TODO: Multi Process
    @SuppressWarnings("unchecked")
    @Override
    public void construct(Key<?> section, Object value) {

        for (Transformer transformer : transformers) {
            try {

                if (!transformer.canConstruct(value))
                    continue;

                transformer.constructSection(section, value, configurator);
            } catch (Exception ignored) {
                ignored.printStackTrace();
            }

        }
    }

    @Override
    public Collection<Transformer<?>> getTransformers() {
        return Collections.unmodifiableList(transformers);
    }

    @Override
    public void addTransformer(Transformer<?> transformer) {
        if(!transformers.contains(transformer))
            transformers.add(transformer);
    }

    @Override
    public void removeTransformer(Transformer<?> transformer) {
        transformers.remove(transformer);
    }
}
