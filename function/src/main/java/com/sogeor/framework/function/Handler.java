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
 * Представляет собой обработчик (1) объектов (2).
 *
 * @param <T> тип [2].
 * @param <R> тип объектов (3), возвращаемых (1).
 * @param <F> тип программного сбоя или неисправности, возникающей при неудачной обработке [2] или возврате [3].
 *
 * @since 1.0.0-RC1
 */
@FunctionalInterface
public interface Handler<T, R, F extends Throwable> {

    /**
     * Создаёт обработчик (2) объектов (3) с методом {@linkplain #handle(Object)}, возвращающим (1).
     *
     * @param object объект (1).
     * @param <T> тип [3].
     * @param <R> тип объектов (4), возвращаемых [2].
     * @param <F> тип программного сбоя или неисправности, возникающей при неудачной обработке [3] или возврате [4].
     *
     * @return [2].
     *
     * @since 1.0.0-RC1
     */
    @Contract("? -> new")
    static <T, R, F extends Throwable> @NonNull Handler<T, R, F> of(final @Nullable R object) {
        return ignored -> object;
    }

    /**
     * Обрабатывает [1] и возвращает, предположительно, другой объект (2).
     *
     * @param object объект (1).
     *
     * @return [2].
     *
     * @throws ValidationFault неудачная валидация, предположительно, [1] и/или [2].
     * @throws F неудачная обработка [1] или возврат [2].
     * @since 1.0.0-RC1
     */
    @Contract("? -> ?")
    @Nullable
    R handle(final @Nullable T object) throws ValidationFault, F;

    /**
     * Создаёт обработчик (2) объектов с методом {@linkplain #handle(Object)}, выполняющим сначала метод
     * {@linkplain #handle(Object) this.handle(Object)}, а потом получающим от метода
     * {@linkplain #handle(Object) handler.handle(Object)} объект (3) и возвращающим [3].
     *
     * @param handler обработчик (1) объектов.
     * @param <R2> тип объектов, возвращаемых [1].
     *
     * @return [2].
     *
     * @throws ValidationFault неудачная валидация [1].
     * @since 1.0.0-RC1
     */
    @Contract("!null -> new; null -> fault")
    default <R2> @Nullable Handler<T, R2, F> and(final @NonNull Handler<? super R, R2, ? extends F> handler) throws
                                                                                                             ValidationFault {
        Validator.nonNull(handler, "The passed handler");
        return object -> handler.handle(handle(object));
    }

    /**
     * Создаёт обработчик (2) объектов с методом {@linkplain #handle(Object)}, пытающимся получить сначала от метода
     * {@linkplain #handle(Object) this.handle(Object)}, а потом, если неудачно, от метода
     * {@linkplain #handle(Object) handler.handle(Object)} объект (3) и вернуть [3].
     *
     * @param handler обработчик (1) объектов.
     *
     * @return [2].
     *
     * @throws ValidationFault неудачная валидация [1].
     * @since 1.0.0-RC1
     */
    @Contract("!null -> new; null -> fault")
    default @Nullable Handler<T, R, F> or(final @NonNull Handler<? super T, ? extends R, ? extends F> handler) throws
                                                                                                               ValidationFault {
        Validator.nonNull(handler, "The passed handler");
        return object -> {
            try {
                return handle(object);
            } catch (final @NonNull Throwable primary) {
                try {
                    return handler.handle(object);
                } catch (final @NonNull Throwable secondary) {
                    primary.addSuppressed(secondary);
                    throw primary;
                }
            }
        };
    }

}
