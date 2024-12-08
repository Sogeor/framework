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

package com.sogeor.validation;

import com.sogeor.annotation.Contract;
import com.sogeor.annotation.NonNull;
import com.sogeor.annotation.Nullable;
import com.sogeor.throwable.failure.utility.UtilityCreationFailure;

/**
 * Представляет собой валидатор объектов и значений.
 *
 * @since 1.0.0-RC1
 */
public final class Validator {

    /**
     * Генерирует {@linkplain UtilityCreationFailure проверяемый программный сбой} с
     * {@linkplain UtilityCreationFailure#TEMPLATE_MESSAGE шаблонным сообщением} на основе имени этого класса.
     *
     * @throws UtilityCreationFailure создание экземпляра запрещено.
     * @since 1.0.0-RC1
     */
    @Contract("-> failure")
    private Validator() throws UtilityCreationFailure {
        throw new UtilityCreationFailure(UtilityCreationFailure.TEMPLATE_MESSAGE.formatted("the Validator"));
    }

    /**
     * Если переданный объект нулевой, то возвращает его, в противном случае генерирует
     * {@linkplain NonNullValidationFault непроверяемую программную неисправность}.
     *
     * @param object объект.
     * @param <T> тип переданного объекта.
     *
     * @return Переданный объект, если он нулевой.
     *
     * @throws NonNullValidationFault объект должен быть нулевым.
     * @see #isNull(Object, String)
     * @since 1.0.0-RC1
     */
    @Contract("null -> null; !null -> fault")
    public static <T> @Nullable T isNull(final @Nullable T object) throws NonNullValidationFault {
        if (object == null) return null;
        throw new NonNullValidationFault();
    }

    /**
     * Если переданный объект нулевой, то возвращает его, в противном случае генерирует
     * {@linkplain NonNullValidationFault непроверяемую программную неисправность}  с
     * {@linkplain NonNullValidationFault#TEMPLATE_MESSAGE шаблонным сообщением} на основе переданного имени переданного
     * объекта.
     *
     * @param object объект.
     * @param name имя переданного объекта.
     * @param <T> тип переданного объекта.
     *
     * @return Переданный объект, если он нулевой.
     *
     * @throws NonNullValidationFault объект должен быть нулевым.
     * @see #isNull(Object)
     * @since 1.0.0-RC1
     */
    @Contract("null, ? -> null; !null, ? -> fault")
    public static <T> @Nullable T isNull(final @Nullable T object, final @Nullable String name) throws
                                                                                                NonNullValidationFault {
        if (object == null) return null;
        if (name == null) throw new NonNullValidationFault();
        throw new NonNullValidationFault(NonNullValidationFault.TEMPLATE_MESSAGE.formatted(name));
    }

    /**
     * Если переданный объект ненулевой, то возвращает его, в противном случае генерирует
     * {@linkplain NullValidationFault непроверяемую программную неисправность}.
     *
     * @param object объект.
     * @param <T> тип переданного объекта.
     *
     * @return Переданный объект, если он ненулевой.
     *
     * @throws NullValidationFault объект не должен быть нулевым.
     * @see #notNull(Object, String)
     * @since 1.0.0-RC1
     */
    @Contract("!null -> 1; null -> fault")
    public static <T> @NonNull T notNull(final @Nullable T object) throws NullValidationFault {
        if (object != null) return object;
        throw new NullValidationFault();
    }

    /**
     * Если переданный объект ненулевой, то возвращает его, в противном случае генерирует
     * {@linkplain NullValidationFault непроверяемую программную неисправность}  с
     * {@linkplain NullValidationFault#TEMPLATE_MESSAGE шаблонным сообщением} на основе переданного имени переданного
     * объекта.
     *
     * @param object объект.
     * @param name имя переданного объекта.
     * @param <T> тип переданного объекта.
     *
     * @return Переданный объект, если он ненулевой.
     *
     * @throws NullValidationFault объект не должен быть нулевым.
     * @see #notNull(Object)
     * @since 1.0.0-RC1
     */
    @Contract("!null, ? -> 1; null, ? -> fault")
    public static <T> @NonNull T notNull(final @Nullable T object, final @Nullable String name) throws
                                                                                                NullValidationFault {
        if (object != null) return object;
        if (name == null) throw new NullValidationFault();
        throw new NullValidationFault(NullValidationFault.TEMPLATE_MESSAGE.formatted(name));
    }

    /**
     * Если переданное значение ложное, то возвращает его, в противном случае генерирует
     * {@linkplain TrueValidationFault непроверяемую программную неисправность}.
     *
     * @param value значение.
     *
     * @return Переданное значение, если оно ложное.
     *
     * @throws TrueValidationFault условие не должно быть истинным.
     * @see #isFalse(boolean, String)
     * @since 1.0.0-RC1
     */
    @Contract("false -> false; true -> fault")
    public static boolean isFalse(final boolean value) throws TrueValidationFault {
        if (!value) return false;
        throw new TrueValidationFault();
    }

    /**
     * Если переданное значение ложное, то возвращает его, в противном случае генерирует
     * {@linkplain TrueValidationFault непроверяемую программную неисправность} с
     * {@linkplain TrueValidationFault#TEMPLATE_MESSAGE шаблонным сообщением} на основе переданного имени переданного
     * значения.
     *
     * @param value значение.
     * @param name имя переданного значения.
     *
     * @return Переданное значение, если оно ложное.
     *
     * @throws TrueValidationFault условие не должно быть истинным.
     * @see #isFalse(boolean)
     * @since 1.0.0-RC1
     */
    @Contract("false, ? -> false; true, ? -> fault")
    public static boolean isFalse(final boolean value, final @Nullable String name) throws TrueValidationFault {
        if (!value) return false;
        if (name == null) throw new TrueValidationFault();
        throw new TrueValidationFault(TrueValidationFault.TEMPLATE_MESSAGE.formatted(name));
    }

    /**
     * Если переданное значение истинное, то возвращает его, в противном случае генерирует
     * {@linkplain FalseValidationFault непроверяемую программную неисправность}.
     *
     * @param value значение.
     *
     * @return Переданное значение, если оно истинное.
     *
     * @throws FalseValidationFault условие не должно быть ложным.
     * @see #isTrue(boolean, String)
     * @since 1.0.0-RC1
     */
    @Contract("true -> true; false -> fault")
    public static boolean isTrue(final boolean value) throws FalseValidationFault {
        if (value) return true;
        throw new FalseValidationFault();
    }

    /**
     * Если переданное значение истинное, то возвращает его, в противном случае генерирует
     * {@linkplain FalseValidationFault непроверяемую программную неисправность} с
     * {@linkplain FalseValidationFault#TEMPLATE_MESSAGE шаблонным сообщением} на основе переданного имени переданного
     * значения.
     *
     * @param value значение.
     * @param name имя переданного значения.
     *
     * @return Переданное значение, если оно истинное.
     *
     * @throws FalseValidationFault условие не должно быть истинным.
     * @see #isTrue(boolean)
     * @since 1.0.0-RC1
     */
    @Contract("true, ? -> true; false, ? -> fault")
    public static boolean isTrue(final boolean value, final @Nullable String name) throws FalseValidationFault {
        if (value) return true;
        if (name == null) throw new FalseValidationFault();
        throw new FalseValidationFault(FalseValidationFault.TEMPLATE_MESSAGE.formatted(name));
    }

}
