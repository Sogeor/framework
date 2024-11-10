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

package com.sogeor.common.optional;

import com.sogeor.function.Callback;
import com.sogeor.function.Consumer;
import com.sogeor.function.Supplier;
import com.sogeor.throwable.failure.CheckedFailure;
import com.sogeor.throwable.failure.UncheckedFailure;
import com.sogeor.throwable.fault.CheckedFault;
import com.sogeor.throwable.fault.UncheckedFault;
import com.sogeor.validation.ValidationFault;
import com.sogeor.validation.argument.ArgumentValidator;
import com.sogeor.validation.value.ValueValidator;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Представляет собой класс-обёртку, который содержит либо ненулевой, либо нулевой объёкт.
 *
 * @since 1.0.0-RC1
 */
public final class Optional<T> {

    /**
     * @since 1.0.0-RC1
     */
    private final @Nullable T object;

    /**
     * @since 1.0.0-RC1
     */
    @Contract(pure = true)
    public Optional() {
        this(null);
    }

    /**
     * @since 1.0.0-RC1
     */
    @Contract(pure = true)
    public Optional(final @Nullable T object) {
        this.object = object;
    }

    /**
     * @since 1.0.0-RC1
     */
    @Contract(pure = true)
    public @Nullable T get() {
        return object;
    }

    /**
     * @since 1.0.0-RC1
     */
    @Contract(pure = true)
    public boolean empty() {
        return object == null;
    }

    /**
     * @since 1.0.0-RC1
     */
    @Contract(pure = true)
    public boolean contains() {
        return object != null;
    }

    /**
     * @since 1.0.0-RC1
     */
    @Contract(pure = true)
    public @NotNull T orPassed(final @NotNull T object) throws ValidationFault {
        ArgumentValidator.notNull(object, "The passed object");
        return contains() ? this.object : object;
    }

    /**
     * @since 1.0.0-RC1
     */
    @Contract(pure = true)
    public <F extends Throwable> @NotNull T orSupplied(final @NotNull Supplier<T, F> supplier) throws ValidationFault,
                                                                                                      F {
        ArgumentValidator.notNull(supplier, "The passed supplier");
        return contains() ? object :
               ValueValidator.notNull(supplier.supply(), "An object supplied by the passed supplier");
    }

    /**
     * @since 1.0.0-RC1
     */
    @Contract(pure = true)
    public <RF extends CheckedFailure, F extends Throwable> @NotNull T orCheckedFailure(
            final @NotNull Supplier<RF, F> supplier) throws ValidationFault, RF, F {
        ArgumentValidator.notNull(supplier, "The passed supplier");
        if (contains()) return object;
        throw ValueValidator.notNull(supplier.supply(), "A checked failure supplied by the passed supplier");
    }

    /**
     * @since 1.0.0-RC1
     */
    @Contract(pure = true)
    public <RF extends UncheckedFailure, F extends Throwable> @NotNull T orUncheckedFailure(
            final @NotNull Supplier<RF, F> supplier) throws ValidationFault, RF, F {
        ArgumentValidator.notNull(supplier, "The passed supplier");
        if (contains()) return object;
        throw ValueValidator.notNull(supplier.supply(), "A unchecked failure supplied by the passed supplier");
    }

    /**
     * @since 1.0.0-RC1
     */
    @Contract(pure = true)
    public <RF extends CheckedFault, F extends Throwable> @NotNull T orCheckedFault(
            final @NotNull Supplier<RF, F> supplier) throws ValidationFault, RF, F {
        ArgumentValidator.notNull(supplier, "The passed supplier");
        if (contains()) return object;
        throw ValueValidator.notNull(supplier.supply(), "A checked fault supplied by the passed supplier");
    }

    /**
     * @since 1.0.0-RC1
     */
    @Contract(pure = true)
    public @NotNull T orUncheckedFault() throws OptionalInstanceFault {
        if (contains()) return object;
        throw new OptionalInstanceFault();
    }

    /**
     * @since 1.0.0-RC1
     */
    @Contract(pure = true)
    public <RF extends UncheckedFault, F extends Throwable> @NotNull T orUncheckedFault(
            final @NotNull Supplier<RF, F> supplier) throws ValidationFault, RF, F {
        ArgumentValidator.notNull(supplier, "The passed supplier");
        if (contains()) return object;
        throw ValueValidator.notNull(supplier.supply(), "A unchecked fault supplied by the passed supplier");
    }

    /**
     * @since 1.0.0-RC1
     */
    @Contract(pure = true)
    public <RF extends Throwable, F extends Throwable> @NotNull T orThrowable(
            final @NotNull Supplier<RF, F> supplier) throws ValidationFault, RF, F {
        ArgumentValidator.notNull(supplier, "The passed supplier");
        if (contains()) return object;
        throw ValueValidator.notNull(supplier.supply(), "A throwable supplied by the passed supplier");
    }

    /**
     * @since 1.0.0-RC1
     */
    @Contract("_ -> this")
    public <F extends Throwable> @NotNull Optional<T> whenEmpty(final @NotNull Callback<F> callback) throws
                                                                                                     ValidationFault,
                                                                                                     F {
        ArgumentValidator.notNull(callback, "The passed callback");
        if (empty()) callback.call();
        return this;
    }

    /**
     * @since 1.0.0-RC1
     */
    @Contract("_ -> this")
    public <F extends Throwable> @NotNull Optional<T> whenContains(final @NotNull Callback<F> callback) throws
                                                                                                        ValidationFault,
                                                                                                        F {
        ArgumentValidator.notNull(callback, "The passed callback");
        if (contains()) callback.call();
        return this;
    }

    /**
     * @since 1.0.0-RC1
     */
    @Contract("_ -> this")
    public <F extends Throwable> @NotNull Optional<T> whenContains(final @NotNull Consumer<T, F> consumer) throws
                                                                                                           ValidationFault,
                                                                                                           F {
        ArgumentValidator.notNull(consumer, "The passed consumer");
        if (contains()) consumer.consume(object);
        return this;
    }

    /**
     * @since 1.0.0-RC1
     */
    @Override
    public int hashCode() {
        return contains() ? object.hashCode() : 0;
    }

    /**
     * @since 1.0.0-RC1
     */
    @Override
    public boolean equals(final @Nullable Object object) {
        return object instanceof Optional<?> optional &&
               (this.object == optional.object || contains() && this.object.equals(optional.object));
    }

    /**
     * @since 1.0.0-RC1
     */
    @Override
    public String toString() {
        return super.toString();
    }

}
