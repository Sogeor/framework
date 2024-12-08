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
import com.sogeor.annotation.Null;
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
    public static <T> @Null T isNull(final @Nullable T object) throws NonNullValidationFault {
        if (object == null) return null;
        throw new NonNullValidationFault();
    }

    /**
     * Если переданный объект нулевой, то возвращает его, в противном случае генерирует
     * {@linkplain NonNullValidationFault непроверяемую программную неисправность} с
     * {@linkplain NonNullValidationFault#TEMPLATE_MESSAGE шаблонным сообщением} на основе переданного имени переданного
     * объекта, или если оно нулевое, то с {@linkplain NonNullValidationFault#DEFAULT_CAUSE сообщением по умолчанию}.
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
    public static <T> @Null T isNull(final @Nullable T object, final @Nullable String name) throws
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
     * {@linkplain NullValidationFault непроверяемую программную неисправность} с
     * {@linkplain NullValidationFault#TEMPLATE_MESSAGE шаблонным сообщением} на основе переданного имени переданного
     * объекта, или если оно нулевое, то с {@linkplain NullValidationFault#DEFAULT_CAUSE сообщением по умолчанию}.
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
     * @throws TrueValidationFault условие должно быть ложным.
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
     * значения, или если оно нулевое, то с {@linkplain TrueValidationFault#DEFAULT_CAUSE сообщением по умолчанию}.
     *
     * @param value значение.
     * @param name имя переданного значения.
     *
     * @return Переданное значение, если оно ложное.
     *
     * @throws TrueValidationFault условие должно быть ложным.
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
     * @throws FalseValidationFault условие должно быть истинным.
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
     * значения, или если оно нулевое, то с {@linkplain FalseValidationFault#DEFAULT_CAUSE сообщением по умолчанию}.
     *
     * @param value значение.
     * @param name имя переданного значения.
     *
     * @return Переданное значение, если оно истинное.
     *
     * @throws FalseValidationFault условие должно быть истинным.
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
     * Если переданные первичный и вторичный объекты равны, то возвращает переданный первичный объект, в противном
     * случае генерирует {@linkplain NonEqualValidationFault непроверяемую программную неисправность} с
     * {@linkplain NonEqualValidationFault#DEFAULT_OBJECTS_MESSAGE сообщением для объектов по умолчанию}.
     *
     * @param primaryObject первичный объект.
     * @param secondaryObject вторичный объект.
     * @param <T> тип переданных первичного и вторичного объектов.
     *
     * @return Переданный первичный объект, если он равен переданному вторичному объекту.
     *
     * @throws NonEqualValidationFault переданные первичный и вторичный объекты должны быть равны.
     * @see #equal(Object, Object, String, String)
     * @since 1.0.0-RC1
     */
    @Contract("?, ? -> ?")
    public static <T> @Nullable T equal(final @Nullable T primaryObject, final @Nullable T secondaryObject) throws
                                                                                                            NonEqualValidationFault {
        if (primaryObject == secondaryObject || primaryObject != null && primaryObject.equals(secondaryObject))
            return primaryObject;
        throw new NonEqualValidationFault(NonEqualValidationFault.DEFAULT_OBJECTS_MESSAGE);
    }

    /**
     * Если переданные первичный и вторичный объекты равны, то возвращает переданный первичный объект, в противном
     * случае генерирует {@linkplain NonEqualValidationFault непроверяемую программную неисправность} с
     * {@linkplain NonEqualValidationFault#TEMPLATE_MESSAGE шаблонным сообщением} на основе переданных имён переданных
     * первичного и вторичного объектов, или если они нулевые, то с
     * {@linkplain NonEqualValidationFault#DEFAULT_OBJECTS_MESSAGE сообщением для объектов по умолчанию}.
     *
     * @param primaryObject первичный объект.
     * @param secondaryObject вторичный объект.
     * @param primaryName имя переданного первичного объекта.
     * @param secondaryName имя переданного вторичного объекта.
     * @param <T> тип переданных первичного и вторичного объектов.
     *
     * @return Переданный первичный объект, если он равен переданному вторичному объекту.
     *
     * @throws NonEqualValidationFault переданные первичный и вторичный объекты должны быть равны.
     * @see #equal(Object, Object)
     * @since 1.0.0-RC1
     */
    @Contract("?, ? -> ?")
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
     * Если переданные первичный и вторичный объекты неравны, то возвращает переданный первичный объект, в противном
     * случае генерирует {@linkplain EqualValidationFault непроверяемую программную неисправность} с
     * {@linkplain EqualValidationFault#DEFAULT_OBJECTS_MESSAGE сообщением для объектов по умолчанию}.
     *
     * @param primaryObject первичный объект.
     * @param secondaryObject вторичный объект.
     * @param <T> тип переданных первичного и вторичного объектов.
     *
     * @return Переданный первичный объект, если он неравен переданному вторичному объекту.
     *
     * @throws EqualValidationFault переданные первичный и вторичный объекты не должны быть равны.
     * @see #nonEqual(Object, Object, String, String)
     * @since 1.0.0-RC1
     */
    @Contract("?, ? -> ?")
    public static <T> @Nullable T nonEqual(final @Nullable T primaryObject, final @Nullable T secondaryObject) throws
                                                                                                               EqualValidationFault {
        if (primaryObject == secondaryObject || primaryObject != null && primaryObject.equals(secondaryObject))
            return primaryObject;
        throw new EqualValidationFault(EqualValidationFault.DEFAULT_OBJECTS_MESSAGE);
    }

    /**
     * Если переданные первичный и вторичный объекты неравны, то возвращает переданный первичный объект, в противном
     * случае генерирует {@linkplain EqualValidationFault непроверяемую программную неисправность} с
     * {@linkplain EqualValidationFault#TEMPLATE_MESSAGE шаблонным сообщением} на основе переданных имён переданных
     * первичного и вторичного объектов, или если они нулевые, то с
     * {@linkplain EqualValidationFault#DEFAULT_OBJECTS_MESSAGE сообщением для объектов по умолчанию}.
     *
     * @param primaryObject первичный объект.
     * @param secondaryObject вторичный объект.
     * @param primaryName имя переданного первичного объекта.
     * @param secondaryName имя переданного вторичного объекта.
     * @param <T> тип переданных первичного и вторичного объектов.
     *
     * @return Переданный первичный объект, если он неравен переданному вторичному объекту.
     *
     * @throws EqualValidationFault переданные первичный и вторичный объекты не должны быть равны.
     * @see #nonEqual(Object, Object)
     * @since 1.0.0-RC1
     */
    @Contract("?, ? -> ?")
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
