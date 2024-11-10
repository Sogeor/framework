/*
 * Copyright 2024 Sogeor
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sogeor.function;

import com.sogeor.validation.ValidationFault;
import com.sogeor.validation.argument.ArgumentValidator;
import com.sogeor.validation.value.ValueValidator;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * @since 1.0.0-RC1
 */
@FunctionalInterface
public interface Supplier<T, F extends Throwable> {

    /**
     * @since 1.0.0-RC1
     */
    static <T, F extends Throwable> Supplier<T, F> of(final @NotNull T object) throws ValidationFault {
        ArgumentValidator.notNull(object, "The passed object");
        return () -> object;
    }

    /**
     * @since 1.0.0-RC1
     */
    @NotNull T supply() throws ValidationFault, F;

    /**
     * @since 1.0.0-RC1
     */
    @Contract(value = "_ -> new", pure = true)
    default @NotNull Supplier<? extends T, F> orPassed(final @NotNull T object) throws ValidationFault {
        ArgumentValidator.notNull(object, "The passed object");
        return () -> {
            try {
                return ValueValidator.notNull(supply(), "A object supplied by this supplier");
            } catch (final @NotNull Throwable ignored) {
                return object;
            }
        };
    }

    /**
     * @since 1.0.0-RC1
     */
    @Contract(value = "_ -> new", pure = true)
    default @NotNull Supplier<? extends T, ? extends F> orSupplied(
            final @NotNull Supplier<? extends T, ? extends F> supplier) throws ValidationFault {
        ArgumentValidator.notNull(supplier, "The passed supplier");
        return () -> {
            try {
                return ValueValidator.notNull(supply(), "A object supplied by this supplier");
            } catch (final @NotNull Throwable first) {
                try {
                    return ValueValidator.notNull(supplier.supply(), "A object supplied by the passed supplier");
                } catch (final @NotNull Throwable second) {
                    first.addSuppressed(second);
                    throw first;
                }
            }
        };
    }

}
