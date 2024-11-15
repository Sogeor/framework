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

package com.sogeor.validation.condition;

import com.sogeor.throwable.failure.utility.UtilityCreationFailure;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

/**
 * Представляет собой валидатор истинности и ложности условий.
 *
 * @see #isFalse(boolean)
 * @see #isFalse(boolean, String)
 * @see #isTrue(boolean)
 * @see #isTrue(boolean, String)
 * @since 1.0.0-RC1
 */
public final class ConditionValidator {

    /**
     * Генерирует проверяемый сбой.
     *
     * @throws UtilityCreationFailure не допускается создание экземпляра этого утилитарного класса.
     * @apiNote Этот конструктор предназначен для предотвращения создания экземпляра этого утилитарного класса.
     * @since 1.0.0-RC1
     */
    @Contract("-> fail")
    private ConditionValidator() throws UtilityCreationFailure {
        throw new UtilityCreationFailure(
                UtilityCreationFailure.TEMPLATE_MESSAGE.formatted("the ConditionValidator utility class"));
    }

    /**
     * Валидирует условие и, если оно ложное, возвращает ложь, в противном случае генерирует непроверяемую программную
     * неисправность.
     *
     * @param value истинность или ложность условия.
     *
     * @throws TrueConditionValidationFault не допускается, чтобы условие было истинным.
     * @see #isFalse(boolean, String)
     * @since 1.0.0-RC1
     */
    @Contract("false -> false; true -> fail")
    public static boolean isFalse(final boolean value) throws TrueConditionValidationFault {
        if (!value) return false;
        throw new TrueConditionValidationFault();
    }

    /**
     * Валидирует условие и, если оно ложное, возвращает ложь, в противном случае генерирует непроверяемую программную
     * неисправность с шаблонным сообщением на основе имени условия.
     *
     * @param value истинность или ложность условия.
     * @param name имя условия.
     *
     * @throws TrueConditionValidationFault не допускается, чтобы условие было истинным.
     * @see #isFalse(boolean)
     * @since 1.0.0-RC1
     */
    @Contract("false, _ -> false; true, _ -> fail")
    public static boolean isFalse(final boolean value, final @Nullable String name) throws
                                                                                    TrueConditionValidationFault {
        if (!value) return false;
        if (name == null) throw new TrueConditionValidationFault();
        throw new TrueConditionValidationFault(TrueConditionValidationFault.TEMPLATE_MESSAGE.formatted(name));
    }

    /**
     * Валидирует условие и, если оно истинно, возвращает истину, в противном случае генерирует непроверяемую
     * программную неисправность.
     *
     * @param value истинность или ложность условия.
     *
     * @throws FalseConditionValidationFault не допускается, чтобы условие было ложным.
     * @see #isTrue(boolean, String)
     * @since 1.0.0-RC1
     */
    @Contract("true -> true; false -> fail")
    public static boolean isTrue(final boolean value) throws FalseConditionValidationFault {
        if (value) return true;
        throw new FalseConditionValidationFault();
    }

    /**
     * Валидирует условие и, если оно истинно, возвращает истину, в противном случае генерирует непроверяемую
     * программную неисправность с шаблонным сообщением на основе имени условия.
     *
     * @param value истинность или ложность условия.
     * @param name имя условия.
     *
     * @throws FalseConditionValidationFault не допускается, чтобы условие было ложным.
     * @see #isTrue(boolean)
     * @since 1.0.0-RC1
     */
    @Contract("true, _ -> true; false, _ -> fail")
    public static boolean isTrue(final boolean value, final @Nullable String name) throws
                                                                                   FalseConditionValidationFault {
        if (value) return true;
        if (name == null) throw new FalseConditionValidationFault();
        throw new FalseConditionValidationFault(FalseConditionValidationFault.TEMPLATE_MESSAGE.formatted(name));
    }

}
