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
import com.sogeor.framework.throwable.fault.ImaginaryFault;
import com.sogeor.framework.validation.ValidationFault;
import com.sogeor.framework.validation.Validator;

/**
 * Представляет собой поставщик объекта.
 *
 * @param <T> тип объекта.
 * @param <F> тип программного сбоя или неисправности, возникающий при неудачной поставке объекта.
 *
 * @since 1.0.0-RC1
 */
@FunctionalInterface
public interface Supplier<T, F extends Throwable> {

    /**
     * Создаёт экземпляр, который поставляет переданный в этот метод объект.
     *
     * @param object объект.
     * @param <T> тип объекта.
     *
     * @return Новый экземпляр, который поставляет переданный в этот метод объект.
     *
     * @since 1.0.0-RC1
     */
    @Contract("? -> new")
    static <T> @NonNull Supplier<T, ImaginaryFault> of(final @Nullable T object) {
        return () -> object;
    }

    /**
     * Поставляет объект.
     *
     * @return Объект.
     *
     * @throws ValidationFault неудачная валидация.
     * @throws F неудачная поставка объекта.
     * @since 1.0.0-RC1
     */
    @Contract("-> ?")
    @Nullable
    T supply() throws ValidationFault, F;

    /**
     * Создаёт экземпляр, который пытается получить и вернуть объект, поставляемый этим экземпляром, а если неудачно, то
     * поставляет переданный в этот метод объект.
     *
     * @param object объект.
     *
     * @return Новый экземпляр, который пытается получить и вернуть объект, поставляемый этим экземпляром, а если
     * неудачно, то поставляет переданный в этот метод объект.
     *
     * @since 1.0.0-RC1
     */
    @Contract("? -> new")
    default @NonNull Supplier<T, F> orPassed(final @Nullable T object) {
        return () -> {
            try {
                return supply();
            } catch (final @NonNull Throwable ignored) {
                return object;
            }
        };
    }

    /**
     * @since 1.0.0-RC1
     */
    @Contract("!null -> new; null -> fault")
    default <T2 extends T, F2 extends F> @NonNull Supplier<T, F> orSupplied(
            final @NonNull Supplier<T2, F2> supplier) throws ValidationFault {
        Validator.nonNull(supplier, "The passed supplier");
        return () -> {
            try {
                return supply();
            } catch (final @NonNull Throwable primary) {
                try {
                    return supplier.supply();
                } catch (final @NonNull Throwable secondary) {
                    primary.addSuppressed(secondary);
                    throw primary;
                }
            }
        };
    }

}
