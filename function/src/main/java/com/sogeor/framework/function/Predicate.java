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

package com.sogeor.framework.function;

import com.sogeor.framework.annotation.Contract;
import com.sogeor.framework.annotation.NonNull;
import com.sogeor.framework.annotation.Nullable;
import com.sogeor.framework.validation.ValidationFault;
import com.sogeor.framework.validation.Validator;

/**
 * Представляет собой предикат (1) объектов (2).
 *
 * @param <T> тип [2].
 * @param <F> тип программного сбоя или неисправности, возникающей при неудачной оценке [2].
 *
 * @since 1.0.0-RC1
 */
@FunctionalInterface
public interface Predicate<T, F extends Throwable> {

    /**
     * Создаёт предикат (2) объектов (3) с методом {@linkplain #evaluate(Object)}, возвращающим (1).
     *
     * @param value оценка (1).
     * @param <T> тип [3].
     * @param <F> тип программного сбоя или неисправности, возникающей при неудачной оценке [3].
     *
     * @return [2].
     *
     * @since 1.0.0-RC1
     */
    @Contract("? -> new")
    static <T, F extends Throwable> @NonNull Predicate<T, F> of(final boolean value) {
        return ignored -> value;
    }

    /**
     * Оценивает [1] и возвращает его оценку (2).
     *
     * @param object объект (1).
     *
     * @return [2].
     *
     * @throws ValidationFault неудачная валидация, предположительно, [1].
     * @throws F неудачная обработка [1].
     * @since 1.0.0-RC1
     */
    @Contract("? -> ?")
    boolean evaluate(final @Nullable T object) throws ValidationFault, F;

    /**
     * Создаёт предикат (1) объектов с методом {@linkplain #evaluate(Object)}, получающим от метода
     * {@linkplain #evaluate(Object) this.evaluate(Object)} оценку и возвращающим её инверсию.
     *
     * @return [1].
     *
     * @since 1.0.0-RC1
     */
    @Contract("-> new")
    default @NonNull Predicate<T, F> not() {
        return object -> !evaluate(object);
    }

    /**
     * Создаёт предикат (2) объектов с методом {@linkplain #evaluate(Object)}, получающим от методов
     * {@linkplain #evaluate(Object) this.evaluate(Object)} и {@linkplain #evaluate(Object) predicate.evaluate(Object)}
     * оценки и возвращающим их конъюнкцию.
     *
     * @param predicate предикат (1) объектов.
     *
     * @return [2].
     *
     * @throws ValidationFault неудачная валидация [1].
     * @since 1.0.0-RC1
     */
    @Contract("!null -> new; null -> fault")
    default @NonNull Predicate<T, F> and(final @NonNull Predicate<? super T, ? extends F> predicate) throws
                                                                                                     ValidationFault {
        Validator.nonNull(predicate, "The passed predicate");
        return object -> evaluate(object) && predicate.evaluate(object);
    }

    /**
     * Создаёт предикат (2) объектов с методом {@linkplain #evaluate(Object)}, получающим от методов
     * {@linkplain #evaluate(Object) this.evaluate(Object)} и {@linkplain #evaluate(Object) predicate.evaluate(Object)}
     * оценки и возвращающим их штрих Шеффера.
     *
     * @param predicate предикат (1) объектов.
     *
     * @return [2].
     *
     * @throws ValidationFault неудачная валидация [1].
     * @since 1.0.0-RC1
     */
    @Contract("!null -> new; null -> fault")
    default @NonNull Predicate<T, F> nand(final @NonNull Predicate<? super T, ? extends F> predicate) throws
                                                                                                      ValidationFault {
        Validator.nonNull(predicate, "The passed predicate");
        return object -> !(evaluate(object) && predicate.evaluate(object));
    }

    /**
     * Создаёт предикат (2) объектов с методом {@linkplain #evaluate(Object)}, получающим от методов
     * {@linkplain #evaluate(Object) this.evaluate(Object)} и {@linkplain #evaluate(Object) predicate.evaluate(Object)}
     * оценки и возвращающим их мягкую дизъюнкцию.
     *
     * @param predicate предикат (1) объектов.
     *
     * @return [2].
     *
     * @throws ValidationFault неудачная валидация [1].
     * @since 1.0.0-RC1
     */
    @Contract("!null -> new; null -> fault")
    default @NonNull Predicate<T, F> or(final @NonNull Predicate<? super T, ? extends F> predicate) throws
                                                                                                    ValidationFault {
        Validator.nonNull(predicate, "The passed predicate");
        return object -> evaluate(object) || predicate.evaluate(object);
    }

    /**
     * Создаёт предикат (2) объектов с методом {@linkplain #evaluate(Object)}, получающим от методов
     * {@linkplain #evaluate(Object) this.evaluate(Object)} и {@linkplain #evaluate(Object) predicate.evaluate(Object)}
     * оценки и возвращающим их стрелку Пирса.
     *
     * @param predicate предикат (1) объектов.
     *
     * @return [2].
     *
     * @throws ValidationFault неудачная валидация [1].
     * @since 1.0.0-RC1
     */
    @Contract("!null -> new; null -> fault")
    default @NonNull Predicate<T, F> nor(final @NonNull Predicate<? super T, ? extends F> predicate) throws
                                                                                                     ValidationFault {
        Validator.nonNull(predicate, "The passed predicate");
        return object -> !(evaluate(object) || predicate.evaluate(object));
    }

    /**
     * Создаёт предикат (2) объектов с методом {@linkplain #evaluate(Object)}, получающим от методов
     * {@linkplain #evaluate(Object) this.evaluate(Object)} и {@linkplain #evaluate(Object) predicate.evaluate(Object)}
     * оценки и возвращающим их строгую дизъюнкцию.
     *
     * @param predicate предикат (1) объектов.
     *
     * @return [2].
     *
     * @throws ValidationFault неудачная валидация [1].
     * @since 1.0.0-RC1
     */
    @Contract("!null -> new; null -> fault")
    default @NonNull Predicate<T, F> xor(final @NonNull Predicate<? super T, ? extends F> predicate) throws
                                                                                                     ValidationFault {
        Validator.nonNull(predicate, "The passed predicate");
        return object -> evaluate(object) ^ predicate.evaluate(object);
    }

}
