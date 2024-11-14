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

package com.sogeor.validation.value;

import com.sogeor.throwable.failure.utility.UtilityCreationFailure;
import com.sogeor.validation.argument.NonNullArgumentValidationFault;
import com.sogeor.validation.argument.NullArgumentValidationFault;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Представляет собой валидатор анонимных значений.
 *
 * @see #isNull(Object)
 * @see #isNull(Object, String)
 * @see #notNull(Object)
 * @see #notNull(Object, String)
 * @since 1.0.0-RC1
 */
public final class ValueValidator {

    /**
     * Генерирует проверяемый сбой.
     *
     * @throws UtilityCreationFailure не допускается создание экземпляра этого утилитарного класса.
     * @apiNote Этот конструктор предназначен для предотвращения создания экземпляра этого утилитарного класса.
     * @since 1.0.0-RC1
     */
    @Contract("-> fail")
    private ValueValidator() throws UtilityCreationFailure {
        throw new UtilityCreationFailure(
                UtilityCreationFailure.TEMPLATE_MESSAGE.formatted("the ValueValidator utility class"));
    }

    /**
     * Валидирует объект и, если он нулевой, возвращает его, в противном случае генерирует непроверяемую программную
     * неисправность.
     *
     * @param object объект.
     * @param <T> тип объекта.
     *
     * @throws NonNullArgumentValidationFault не допускается, чтобы объект был ненулевым.
     * @see #isNull(Object, String)
     * @since 1.0.0-RC1
     */
    @Contract("null -> null; !null -> fail")
    public static <T> @Nullable T isNull(final @Nullable T object) throws NonNullValueValidationFault {
        if (object == null) return null;
        throw new NonNullValueValidationFault();
    }

    /**
     * Валидирует объект и, если он нулевой, возвращает его, в противном случае генерирует непроверяемую программную
     * неисправность с шаблонным сообщением на основе имени объекта.
     *
     * @param object объект.
     * @param name имя объекта.
     * @param <T> тип объекта.
     *
     * @throws NonNullArgumentValidationFault не допускается, чтобы объект был ненулевым.
     * @see #isNull(Object)
     * @since 1.0.0-RC1
     */
    @Contract("null, _ -> null; !null, _ -> fail")
    public static <T> @Nullable T isNull(final @Nullable T object, final @Nullable String name) throws
                                                                                                NonNullValueValidationFault {
        if (object == null) return null;
        if (name == null) throw new NonNullValueValidationFault();
        throw new NonNullValueValidationFault(NonNullValueValidationFault.TEMPLATE_MESSAGE.formatted(name));
    }

    /**
     * Валидирует объект и, если он ненулевой, возвращает его, в противном случае генерирует непроверяемую программную
     * неисправность.
     *
     * @param object объект.
     * @param <T> тип объекта.
     *
     * @throws NullArgumentValidationFault не допускается, чтобы объект был нулевым.
     * @see #notNull(Object, String)
     * @since 1.0.0-RC1
     */
    @Contract("!null -> param1; null -> fail")
    public static <T> @NotNull T notNull(final @Nullable T object) throws NullValueValidationFault {
        if (object != null) return object;
        throw new NullValueValidationFault();
    }

    /**
     * Валидирует объект и, если он ненулевой, возвращает его, в противном случае генерирует непроверяемую программную
     * неисправность с шаблонным сообщением на основе имени объекта.
     *
     * @param object объект.
     * @param name имя объекта.
     * @param <T> тип объекта.
     *
     * @throws NullArgumentValidationFault не допускается, чтобы объект был нулевым.
     * @see #notNull(Object)
     * @since 1.0.0-RC1
     */
    @Contract("!null, _ -> param1; null, _ -> fail")
    public static <T> @NotNull T notNull(final @Nullable T object, final @Nullable String name) throws
                                                                                                NullValueValidationFault {
        if (object != null) return object;
        if (name == null) throw new NullValueValidationFault();
        throw new NullValueValidationFault(NullValueValidationFault.TEMPLATE_MESSAGE.formatted(name));
    }

}
