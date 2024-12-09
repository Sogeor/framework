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

package com.sogeor.framework.common.optional;

import com.sogeor.framework.annotation.Contract;
import com.sogeor.framework.annotation.NonNull;
import com.sogeor.framework.annotation.Nullable;
import com.sogeor.framework.function.Action;
import com.sogeor.framework.function.Consumer;
import com.sogeor.framework.function.Supplier;
import com.sogeor.framework.throwable.failure.CheckedFailure;
import com.sogeor.framework.throwable.failure.UncheckedFailure;
import com.sogeor.framework.throwable.fault.CheckedFault;
import com.sogeor.framework.throwable.fault.UncheckedFault;
import com.sogeor.framework.validation.ValidationFault;
import com.sogeor.framework.validation.Validator;

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
    @Contract("?")
    public Optional() {
        this(null);
    }

    /**
     * @since 1.0.0-RC1
     */
    @Contract("?")
    public Optional(final @Nullable T object) {
        this.object = object;
    }

    /**
     * @since 1.0.0-RC1
     */
    @Contract("?")
    public @Nullable T get() {
        return object;
    }

    /**
     * @since 1.0.0-RC1
     */
    @Contract("?")
    public boolean empty() {
        return object == null;
    }

    /**
     * @since 1.0.0-RC1
     */
    @Contract("?")
    public boolean contains() {
        return object != null;
    }

    /**
     * @since 1.0.0-RC1
     */
    @Contract("?")
    public @NonNull T orPassed(final @NonNull T object) throws ValidationFault {
        Validator.nonNull(object, "The passed object");
        return contains() ? this.object : object;
    }

    /**
     * @since 1.0.0-RC1
     */
    @Contract("?")
    public <F extends Throwable> @NonNull T orSupplied(final @NonNull Supplier<T, F> supplier) throws ValidationFault,
                                                                                                      F {
        Validator.nonNull(supplier, "The passed supplier");
        return contains() ? object : Validator.nonNull(supplier.get(), "An object supplied by the passed supplier");
    }

    /**
     * @since 1.0.0-RC1
     */
    @Contract("?")
    public <RF extends CheckedFailure, F extends Throwable> @NonNull T orCheckedFailure(
            final @NonNull Supplier<RF, F> supplier) throws ValidationFault, RF, F {
        Validator.nonNull(supplier, "The passed supplier");
        if (contains()) return object;
        throw Validator.nonNull(supplier.get(), "A checked failure supplied by the passed supplier");
    }

    /**
     * @since 1.0.0-RC1
     */
    @Contract("?")
    public <RF extends UncheckedFailure, F extends Throwable> @NonNull T orUncheckedFailure(
            final @NonNull Supplier<RF, F> supplier) throws ValidationFault, RF, F {
        Validator.nonNull(supplier, "The passed supplier");
        if (contains()) return object;
        throw Validator.nonNull(supplier.get(), "A unchecked failure supplied by the passed supplier");
    }

    /**
     * @since 1.0.0-RC1
     */
    @Contract("?")
    public <RF extends CheckedFault, F extends Throwable> @NonNull T orCheckedFault(
            final @NonNull Supplier<RF, F> supplier) throws ValidationFault, RF, F {
        Validator.nonNull(supplier, "The passed supplier");
        if (contains()) return object;
        throw Validator.nonNull(supplier.get(), "A checked fault supplied by the passed supplier");
    }

    /**
     * @since 1.0.0-RC1
     */
    @Contract("?")
    public @NonNull T orUncheckedFault() throws OptionalInstanceFault {
        if (contains()) return object;
        throw new OptionalInstanceFault();
    }

    /**
     * @since 1.0.0-RC1
     */
    @Contract("?")
    public <RF extends UncheckedFault, F extends Throwable> @NonNull T orUncheckedFault(
            final @NonNull Supplier<RF, F> supplier) throws ValidationFault, RF, F {
        Validator.nonNull(supplier, "The passed supplier");
        if (contains()) return object;
        throw Validator.nonNull(supplier.get(), "A unchecked fault supplied by the passed supplier");
    }

    /**
     * @since 1.0.0-RC1
     */
    @Contract("?")
    public <RF extends Throwable, F extends Throwable> @NonNull T orThrowable(
            final @NonNull Supplier<RF, F> supplier) throws ValidationFault, RF, F {
        Validator.nonNull(supplier, "The passed supplier");
        if (contains()) return object;
        throw Validator.nonNull(supplier.get(), "A throwable supplied by the passed supplier");
    }

    /**
     * @since 1.0.0-RC1
     */
    @Contract("_ -> this")
    public <F extends Throwable> @NonNull Optional<T> whenEmpty(final @NonNull Action<F> action) throws
                                                                                                     ValidationFault,
                                                                                                     F {
        Validator.nonNull(action, "The passed callback");
        if (empty()) action.perform();
        return this;
    }

    /**
     * @since 1.0.0-RC1
     */
    @Contract("_ -> this")
    public <F extends Throwable> @NonNull Optional<T> whenContains(final @NonNull Action<F> action) throws
                                                                                                        ValidationFault,
                                                                                                        F {
        Validator.nonNull(action, "The passed callback");
        if (contains()) action.perform();
        return this;
    }

    /**
     * @since 1.0.0-RC1
     */
    @Contract("_ -> this")
    public <F extends Throwable> @NonNull Optional<T> whenContains(final @NonNull Consumer<T, F> consumer) throws
                                                                                                           ValidationFault,
                                                                                                           F {
        Validator.nonNull(consumer, "The passed consumer");
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
