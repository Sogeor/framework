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
import com.sogeor.framework.validation.ValidationFault;
import com.sogeor.framework.validation.Validator;

/**
 * Представляет собой условие (1).
 *
 * @param <F> тип программного сбоя или неисправности, возникающей при неудачном вычислении [1].
 *
 * @since 1.0.0-RC1
 */
@FunctionalInterface
public interface Condition<F extends Throwable> {

    /**
     * Создаёт условие (2) с методом {@linkplain #compute()}, возвращающим [1].
     *
     * @param value результат вычисления (1).
     * @param <F> тип программного сбоя или неисправности, возникающей при неудачном вычислении [2].
     *
     * @return [2].
     *
     * @since 1.0.0-RC1
     */
    @Contract("? -> new")
    static <F extends Throwable> @NonNull Condition<F> of(final boolean value) {
        return () -> value;
    }

    /**
     * Вычисляет это условие (1) и возвращает результат (2) его вычисления.
     *
     * @return [2].
     *
     * @throws ValidationFault неудачная валидация.
     * @throws F неудачное вычисление [1].
     * @since 1.0.0-RC1
     */
    @Contract("-> ?")
    boolean compute() throws ValidationFault, F;

    /**
     * Создаёт условие (1) с методом {@linkplain #compute()}, получающим от метода
     * {@linkplain #compute() this.compute()} результат вычисления и возвращающим его инверсию.
     *
     * @return [1].
     *
     * @since 1.0.0-RC1
     */
    @Contract("-> new")
    default @NonNull Condition<F> not() {
        return () -> !compute();
    }

    /**
     * Создаёт условие (2) с методом {@linkplain #compute()}, получающим от методов
     * {@linkplain #compute() this.compute()} и {@linkplain #compute() condition.compute()} результаты вычислений и
     * возвращающим их конъюнкцию.
     *
     * @param condition условие (1).
     *
     * @return [2].
     *
     * @throws ValidationFault неудачная валидация [1].
     * @since 1.0.0-RC1
     */
    @Contract("!null -> new; null -> fault")
    default @NonNull Condition<F> and(final @NonNull Condition<? extends F> condition) throws ValidationFault {
        Validator.nonNull(condition, "The passed condition");
        return () -> compute() && condition.compute();
    }

    /**
     * Создаёт условие (2) с методом {@linkplain #compute()}, получающим от методов
     * {@linkplain #compute() this.compute()} и {@linkplain #compute() condition.compute()} результаты вычислений и
     * возвращающим их штрих Шеффера.
     *
     * @param condition условие (1).
     *
     * @return [2].
     *
     * @throws ValidationFault неудачная валидация [1].
     * @since 1.0.0-RC1
     */
    @Contract("!null -> new; null -> fault")
    default @NonNull Condition<F> nand(final @NonNull Condition<? extends F> condition) throws ValidationFault {
        Validator.nonNull(condition, "The passed condition");
        return () -> !(compute() && condition.compute());
    }

    /**
     * Создаёт условие (2) с методом {@linkplain #compute()}, получающим от методов
     * {@linkplain #compute() this.compute()} и {@linkplain #compute() condition.compute()} результаты вычислений и
     * возвращающим их мягкую дизъюнкцию.
     *
     * @param condition условие (1).
     *
     * @return [2].
     *
     * @throws ValidationFault неудачная валидация [1].
     * @since 1.0.0-RC1
     */
    @Contract("!null -> new; null -> fault")
    default @NonNull Condition<F> or(final @NonNull Condition<? extends F> condition) throws ValidationFault {
        Validator.nonNull(condition, "The passed condition");
        return () -> compute() || condition.compute();
    }

    /**
     * Создаёт условие (2) с методом {@linkplain #compute()}, получающим от методов
     * {@linkplain #compute() this.compute()} и {@linkplain #compute() condition.compute()} результаты вычислений и
     * возвращающим их стрелку Пирса.
     *
     * @param condition условие (1).
     *
     * @return [2].
     *
     * @throws ValidationFault неудачная валидация [1].
     * @since 1.0.0-RC1
     */
    @Contract("!null -> new; null -> fault")
    default @NonNull Condition<F> nor(final @NonNull Condition<? extends F> condition) throws ValidationFault {
        Validator.nonNull(condition, "The passed condition");
        return () -> !(compute() || condition.compute());
    }

    /**
     * Создаёт условие (2) с методом {@linkplain #compute()}, получающим от методов
     * {@linkplain #compute() this.compute()} и {@linkplain #compute() condition.compute()} результаты вычислений и
     * возвращающим их эквивалентность.
     *
     * @param condition условие (1).
     *
     * @return [2].
     *
     * @throws ValidationFault неудачная валидация [1].
     * @since 1.0.0-RC1
     */
    @Contract("!null -> new; null -> fault")
    default @NonNull Condition<F> xnor(final @NonNull Condition<? extends F> condition) throws ValidationFault {
        Validator.nonNull(condition, "The passed condition");
        return () -> compute() == condition.compute();
    }

    /**
     * Создаёт условие (2) с методом {@linkplain #compute()}, получающим от методов
     * {@linkplain #compute() this.compute()} и {@linkplain #compute() condition.compute()} результаты вычислений и
     * возвращающим их строгую дизъюнкцию.
     *
     * @param condition условие (1).
     *
     * @return [2].
     *
     * @throws ValidationFault неудачная валидация [1].
     * @since 1.0.0-RC1
     */
    @Contract("!null -> new; null -> fault")
    default @NonNull Condition<F> xor(final @NonNull Condition<? extends F> condition) throws ValidationFault {
        Validator.nonNull(condition, "The passed condition");
        return () -> compute() ^ condition.compute();
    }

    /**
     * Создаёт условие (2) с методом {@linkplain #compute()}, получающим от методов
     * {@linkplain #compute() this.compute()} и {@linkplain #compute() condition.compute()} результаты вычислений и
     * возвращающим их импликацию.
     *
     * @param condition условие (1).
     *
     * @return [2].
     *
     * @throws ValidationFault неудачная валидация [1].
     * @since 1.0.0-RC1
     */
    @Contract("!null -> new; null -> fault")
    default @NonNull Condition<F> imply(final @NonNull Condition<? extends F> condition) throws ValidationFault {
        Validator.nonNull(condition, "The passed condition");
        return () -> !compute() || condition.compute();
    }

}
