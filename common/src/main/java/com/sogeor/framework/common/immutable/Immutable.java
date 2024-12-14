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

package com.sogeor.framework.common.immutable;

import com.sogeor.framework.annotation.Contract;
import com.sogeor.framework.annotation.NonNull;
import com.sogeor.framework.annotation.Nullable;
import com.sogeor.framework.function.Action;
import com.sogeor.framework.function.Consumer;
import com.sogeor.framework.throwable.fault.singleton.SingletonCreationFault;
import com.sogeor.framework.validation.ValidationFault;
import com.sogeor.framework.validation.Validator;

/**
 * Представляет собой обёртку над {@linkplain #object неизменяемым объектом} (1).
 *
 * @param <T> тип [1].
 *
 * @since 1.0.0-RC1
 */
public final class Immutable<T> {

    /**
     * Содержит обёртку над неизменяемым {@code null}.
     *
     * @since 1.0.0-RC1
     */
    private static final @NonNull Immutable<?> EMPTY = new Immutable<>();

    /**
     * Содержит неизменяемый объект.
     *
     * @since 1.0.0-RC1
     */
    private final @Nullable T object;

    /**
     * Создаёт экземпляр на основе {@code null}.
     *
     * @throws ValidationFault [1] не должен быть {@code null}.
     * @see #Immutable(Object)
     * @since 1.0.0-RC1
     */
    @SuppressWarnings("DataFlowIssue")
    @Contract("-> ?")
    private Immutable() throws SingletonCreationFault {
        Validator.isNull(EMPTY, "EMPTY");
        this.object = null;
    }

    /**
     * Создаёт экземпляр на основе [1].
     *
     * @param object объект (1).
     *
     * @throws ValidationFault [1] не должен быть {@code null}.
     * @see #Immutable()
     * @since 1.0.0-RC1
     */
    @Contract("$!null -> new; null -> fault")
    private Immutable(final @NonNull T object) throws ValidationFault {
        this.object = Validator.nonNull(object, "object");
    }

    /**
     * @return {@linkplain #EMPTY Обёртка над неизменяемым} {@code null}.
     *
     * @see #of(Object)
     * @since 1.0.0-RC1
     */
    @SuppressWarnings("unchecked")
    @Contract("-> $!null")
    public static <T> @NonNull Immutable<T> empty() {
        return (Immutable<T>) EMPTY;
    }

    /**
     * Если {@code object == null}, то возвращает {@linkplain #EMPTY обёртку (2) над неизменяемым} {@code null}, иначе
     * создаёт и возвращает {@linkplain #Immutable(Object) экземпляр (3) на основе [1]}.
     *
     * @param object объект (1).
     *
     * @return [2] или [3].
     *
     * @see #empty()
     * @since 1.0.0-RC1
     */
    @Contract("null -> $!null; $!null -> new")
    public static <T> @NonNull Immutable<T> of(final @Nullable T object) {
        return object == null ? empty() : new Immutable<>(object);
    }

    /**
     * @return {@linkplain #object Неизменяемый объект}.
     *
     * @since 1.0.0-RC1
     */
    @Contract("-> $?")
    public @Nullable T get() {
        return object;
    }

    /**
     * @return Если {@linkplain #object неизменяемый объект} отсутствует, то {@code true}, иначе {@code false}.
     *
     * @see #present()
     * @since 1.0.0-RC1
     */
    @Contract("-> $?")
    public boolean absent() {
        return object == null;
    }

    /**
     * @return Если {@linkplain #object неизменяемый объект} присутствует, то {@code true}, иначе {@code false}.
     *
     * @see #absent()
     * @since 1.0.0-RC1
     */
    @Contract("-> $?")
    public boolean present() {
        return object != null;
    }

    /**
     * Если {@linkplain #absent()}, то выполняет [1].
     *
     * @param action действие (1).
     *
     * @return {@code this}.
     *
     * @throws ValidationFault [1] не должно быть {@code null}.
     * @since 1.0.0-RC1
     */
    @Contract("$!null -> this; null -> fault")
    public <F extends Throwable> @NonNull Immutable<T> absent(final @NonNull Action<F> action) throws ValidationFault,
                                                                                                      F {
        Validator.nonNull(action, "action");
        if (absent()) action.perform();
        return this;
    }

    /**
     * Если {@linkplain #absent()}, то выполняет метод {@linkplain Consumer#consume(Object) consumer.consume(Object)} с
     * {@code null}.
     *
     * @param consumer потребитель (1) объектов.
     *
     * @return {@code this}.
     *
     * @throws ValidationFault [1] не должно быть {@code null}.
     * @since 1.0.0-RC1
     */
    @Contract("$!null -> this; null -> fault")
    public <F extends Throwable> @NonNull Immutable<T> absent(final @NonNull Consumer<? super T, F> consumer) throws
                                                                                                              ValidationFault,
                                                                                                              F {
        Validator.nonNull(consumer, "consumer");
        if (absent()) consumer.consume(null);
        return this;
    }

    /**
     * Если {@linkplain #present()}, то выполняет [1].
     *
     * @param action действие (1).
     *
     * @return {@code this}.
     *
     * @throws ValidationFault [1] не должно быть {@code null}.
     * @since 1.0.0-RC1
     */
    @Contract("$!null -> this; null -> fault")
    public <F extends Throwable> @NonNull Immutable<T> present(final @NonNull Action<F> action) throws ValidationFault,
                                                                                                       F {
        Validator.nonNull(action, "action");
        if (present()) action.perform();
        return this;
    }

    /**
     * Если {@linkplain #present()}, то выполняет метод {@linkplain Consumer#consume(Object) consumer.consume(Object)} с
     * {@linkplain #object неизменяемым объектом}.
     *
     * @param consumer потребитель (1) объектов.
     *
     * @return {@code this}.
     *
     * @throws ValidationFault [1] не должно быть {@code null}.
     * @since 1.0.0-RC1
     */
    @Contract("$!null -> this; null -> fault")
    public <F extends Throwable> @NonNull Immutable<T> present(final @NonNull Consumer<? super T, F> consumer) throws
                                                                                                               ValidationFault,
                                                                                                               F {
        Validator.nonNull(consumer, "consumer");
        if (present()) consumer.consume(object);
        return this;
    }

}