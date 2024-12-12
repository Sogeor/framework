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

package com.sogeor.framework.validation;

import com.sogeor.framework.annotation.Contract;
import com.sogeor.framework.annotation.NonNull;
import com.sogeor.framework.annotation.Null;
import com.sogeor.framework.annotation.Nullable;
import com.sogeor.framework.throwable.failure.utility.UtilityCreationFailure;

/**
 * Представляет собой валидатор объектов и значений.
 *
 * @since 1.0.0-RC1
 */
public final class Validator {

    /**
     * Генерирует {@linkplain UtilityCreationFailure проверяемый программный сбой} с
     * {@linkplain UtilityCreationFailure#TEMPLATE_MESSAGE шаблонным сообщением} на основе имени этого класса (1).
     *
     * @throws UtilityCreationFailure экземпляр [1] не должен быть создан.
     * @since 1.0.0-RC1
     */
    @Contract("-> failure")
    private Validator() throws UtilityCreationFailure {
        throw new UtilityCreationFailure(UtilityCreationFailure.TEMPLATE_MESSAGE.formatted("the Validator"));
    }

    /**
     * Если [1] нулевой, то возвращает его, в противном случае генерирует
     * {@linkplain NonNullValidationFault непроверяемую программную неисправность}.
     *
     * @param object объект (1).
     * @param <T> тип [1].
     *
     * @return [1].
     *
     * @throws NonNullValidationFault [1] должен быть нулевым.
     * @see #isNull(Object, String)
     * @since 1.0.0-RC1
     */
    @Contract("null -> null; !null -> fault")
    public static <T> @Null T isNull(final @Nullable T object) throws NonNullValidationFault {
        if (object == null) return null;
        throw new NonNullValidationFault();
    }

    /**
     * Если [1] нулевой, то возвращает его, в противном случае генерирует
     * {@linkplain NonNullValidationFault непроверяемую программную неисправность} с
     * {@linkplain NonNullValidationFault#TEMPLATE_MESSAGE шаблонным сообщением} на основе [2], или если оно нулевое, то
     * с {@linkplain NonNullValidationFault#DEFAULT_CAUSE сообщением по умолчанию}.
     *
     * @param object объект (1).
     * @param name имя (2) [1].
     * @param <T> тип [1].
     *
     * @return [1].
     *
     * @throws NonNullValidationFault [1] должен быть нулевым.
     * @see #isNull(Object)
     * @since 1.0.0-RC1
     */
    @Contract("null, ? -> null; !null, ? -> fault")
    public static <T> @Null T isNull(final @Nullable T object, final @Nullable String name) throws
                                                                                            NonNullValidationFault {
        if (object == null) return null;
        if (name == null) throw new NonNullValidationFault();
        throw new NonNullValidationFault(NonNullValidationFault.TEMPLATE_MESSAGE.formatted(name));
    }

    /**
     * Если [1] ненулевой, то возвращает его, в противном случае генерирует
     * {@linkplain NullValidationFault непроверяемую программную неисправность}.
     *
     * @param object объект (1).
     * @param <T> тип [1].
     *
     * @return [1].
     *
     * @throws NullValidationFault [1] не должен быть нулевым.
     * @see #nonNull(Object, String)
     * @since 1.0.0-RC1
     */
    @Contract("!null -> 1; null -> fault")
    public static <T> @NonNull T nonNull(final @Nullable T object) throws NullValidationFault {
        if (object != null) return object;
        throw new NullValidationFault();
    }

    /**
     * Если [1] ненулевой, то возвращает его, в противном случае генерирует
     * {@linkplain NullValidationFault непроверяемую программную неисправность} с
     * {@linkplain NullValidationFault#TEMPLATE_MESSAGE шаблонным сообщением} на основе [2], или если оно нулевое, то с
     * {@linkplain NullValidationFault#DEFAULT_CAUSE сообщением по умолчанию}.
     *
     * @param object объект (1).
     * @param name имя (2) [1].
     * @param <T> тип [1].
     *
     * @return [1].
     *
     * @throws NullValidationFault [1] не должен быть нулевым.
     * @see #nonNull(Object)
     * @since 1.0.0-RC1
     */
    @Contract("!null, ? -> 1; null, ? -> fault")
    public static <T> @NonNull T nonNull(final @Nullable T object, final @Nullable String name) throws
                                                                                                NullValidationFault {
        if (object != null) return object;
        if (name == null) throw new NullValidationFault();
        throw new NullValidationFault(NullValidationFault.TEMPLATE_MESSAGE.formatted(name));
    }

    /**
     * Если [1] ложное, то возвращает его, в противном случае генерирует
     * {@linkplain TrueValidationFault непроверяемую программную неисправность}.
     *
     * @param value значение (1).
     *
     * @return [1].
     *
     * @throws TrueValidationFault [1] должно быть ложным.
     * @see #isFalse(boolean, String)
     * @since 1.0.0-RC1
     */
    @Contract("false -> false; true -> fault")
    public static boolean isFalse(final boolean value) throws TrueValidationFault {
        if (!value) return false;
        throw new TrueValidationFault();
    }

    /**
     * Если [1] ложное, то возвращает его, в противном случае генерирует
     * {@linkplain TrueValidationFault непроверяемую программную неисправность} с
     * {@linkplain TrueValidationFault#TEMPLATE_MESSAGE шаблонным сообщением} на основе [2], или если оно нулевое, то с
     * {@linkplain TrueValidationFault#DEFAULT_CAUSE сообщением по умолчанию}.
     *
     * @param value значение (1).
     * @param name имя (2) [1].
     *
     * @return [1].
     *
     * @throws TrueValidationFault [1] должно быть ложным.
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
     * Если [1] истинное, то возвращает его, в противном случае генерирует
     * {@linkplain FalseValidationFault непроверяемую программную неисправность}.
     *
     * @param value значение (1).
     *
     * @return [1].
     *
     * @throws FalseValidationFault [1] должно быть истинным.
     * @see #isTrue(boolean, String)
     * @since 1.0.0-RC1
     */
    @Contract("true -> true; false -> fault")
    public static boolean isTrue(final boolean value) throws FalseValidationFault {
        if (value) return true;
        throw new FalseValidationFault();
    }

    /**
     * Если [1] истинное, то возвращает его, в противном случае генерирует
     * {@linkplain FalseValidationFault непроверяемую программную неисправность} с
     * {@linkplain FalseValidationFault#TEMPLATE_MESSAGE шаблонным сообщением} на основе [2], или если оно нулевое, то с
     * {@linkplain FalseValidationFault#DEFAULT_CAUSE сообщением по умолчанию}.
     *
     * @param value значение (1).
     * @param name имя (2) [1].
     *
     * @return [1].
     *
     * @throws FalseValidationFault [1] должно быть истинным.
     * @see #isTrue(boolean)
     * @since 1.0.0-RC1
     */
    @Contract("true, ? -> true; false, ? -> fault")
    public static boolean isTrue(final boolean value, final @Nullable String name) throws FalseValidationFault {
        if (value) return true;
        if (name == null) throw new FalseValidationFault();
        throw new FalseValidationFault(FalseValidationFault.TEMPLATE_MESSAGE.formatted(name));
    }

    /**
     * Если [1] и [2] равны, то возвращает [1], в противном случае генерирует
     * {@linkplain NonEqualValidationFault непроверяемую программную неисправность} с
     * {@linkplain NonEqualValidationFault#DEFAULT_OBJECTS_MESSAGE сообщением для объектов по умолчанию}.
     *
     * @param primaryObject первичный объект (1).
     * @param secondaryObject вторичный объект (2).
     * @param <T> тип [1] и [2].
     *
     * @return [1].
     *
     * @throws NonEqualValidationFault [1] и [2] должны быть равны.
     * @see #equal(Object, Object, String, String)
     * @since 1.0.0-RC1
     */
    @Contract("null, null -> null; null, !null -> fault; !null, null -> fault; !null, !null -> ?")
    public static <T> @Nullable T equal(final @Nullable T primaryObject, final @Nullable T secondaryObject) throws
                                                                                                            NonEqualValidationFault {
        if (primaryObject == secondaryObject || primaryObject != null && primaryObject.equals(secondaryObject))
            return primaryObject;
        throw new NonEqualValidationFault(NonEqualValidationFault.DEFAULT_OBJECTS_MESSAGE);
    }

    /**
     * Если [1] и [2] равны, то возвращает [1], в противном случае генерирует
     * {@linkplain NonEqualValidationFault непроверяемую программную неисправность} с
     * {@linkplain NonEqualValidationFault#TEMPLATE_MESSAGE шаблонным сообщением} на основе [3] и [4], или если они
     * нулевые, то с {@linkplain NonEqualValidationFault#DEFAULT_OBJECTS_MESSAGE сообщением для объектов по умолчанию}.
     *
     * @param primaryObject первичный объект (1).
     * @param secondaryObject вторичный объект (2).
     * @param primaryName имя (3) [1].
     * @param secondaryName имя (4) [2].
     * @param <T> тип [1] и [2].
     *
     * @return [1].
     *
     * @throws NonEqualValidationFault [1] и [2] должны быть равны.
     * @see #equal(Object, Object)
     * @since 1.0.0-RC1
     */
    @Contract(
            "null, null, ?, ? -> null; null, !null, ?, ? -> fault; !null, null, ?, ? -> fault; !null, !null, ?, ? -> ?")
    public static <T> @Nullable T equal(final @Nullable T primaryObject, final @Nullable T secondaryObject,
                                        final @Nullable String primaryName, final @Nullable String secondaryName) throws
                                                                                                                  NonEqualValidationFault {
        if (primaryObject == secondaryObject || primaryObject != null && primaryObject.equals(secondaryObject))
            return primaryObject;
        if (primaryName == null || secondaryName == null)
            throw new NonEqualValidationFault(NonEqualValidationFault.DEFAULT_OBJECTS_MESSAGE);
        throw new NonEqualValidationFault(
                NonEqualValidationFault.TEMPLATE_MESSAGE.formatted("%s and %s".formatted(primaryName, secondaryName)));
    }

    /**
     * Если [1] и [2] неравны, то возвращает [1], в противном случае генерирует
     * {@linkplain EqualValidationFault непроверяемую программную неисправность} с
     * {@linkplain EqualValidationFault#DEFAULT_OBJECTS_MESSAGE сообщением для объектов по умолчанию}.
     *
     * @param primaryObject первичный объект (1).
     * @param secondaryObject вторичный объект (2).
     * @param <T> тип [1] и [2].
     *
     * @return [1].
     *
     * @throws EqualValidationFault [1] и [2] не должны быть равны.
     * @see #nonEqual(Object, Object, String, String)
     * @since 1.0.0-RC1
     */
    @Contract("null, !null -> null; !null, null -> 1; null, null -> fault; !null, !null -> ?")
    public static <T> @Nullable T nonEqual(final @Nullable T primaryObject, final @Nullable T secondaryObject) throws
                                                                                                               EqualValidationFault {
        if (primaryObject != secondaryObject && (primaryObject == null || !primaryObject.equals(secondaryObject)))
            return primaryObject;
        throw new EqualValidationFault(EqualValidationFault.DEFAULT_OBJECTS_MESSAGE);
    }

    /**
     * Если [1] и [2] неравны, то возвращает [1], в противном случае генерирует
     * {@linkplain EqualValidationFault непроверяемую программную неисправность} с
     * {@linkplain EqualValidationFault#TEMPLATE_MESSAGE шаблонным сообщением} на основе [3] и [4], или если они
     * нулевые, то с {@linkplain EqualValidationFault#DEFAULT_OBJECTS_MESSAGE сообщением для объектов по умолчанию}.
     *
     * @param primaryObject первичный объект (1).
     * @param secondaryObject вторичный объект (2).
     * @param primaryName имя (3) [1].
     * @param secondaryName имя (4) [2].
     * @param <T> тип [1] и [2].
     *
     * @return [1].
     *
     * @throws EqualValidationFault [1] и [2] не должны быть равны.
     * @see #nonEqual(Object, Object)
     * @since 1.0.0-RC1
     */
    @Contract("null, !null, ?, ? -> null; !null, null, ?, ? -> 1; null, null, ?, ? -> fault; !null, !null, ?, ? -> ?")
    public static <T> @Nullable T nonEqual(final @Nullable T primaryObject, final @Nullable T secondaryObject,
                                           final @Nullable String primaryName,
                                           final @Nullable String secondaryName) throws EqualValidationFault {
        if (primaryObject != secondaryObject && (primaryObject == null || !primaryObject.equals(secondaryObject)))
            return primaryObject;
        if (primaryName == null || secondaryName == null)
            throw new EqualValidationFault(EqualValidationFault.DEFAULT_OBJECTS_MESSAGE);
        throw new EqualValidationFault(
                EqualValidationFault.TEMPLATE_MESSAGE.formatted("%s and %s".formatted(primaryName, secondaryName)));
    }

}
