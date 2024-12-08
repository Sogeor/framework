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
 * Представляет собой обратный вызов.
 *
 * @param <F> тип программного сбоя или неисправности, возникающей во время выполнения обратного вызова.
 *
 * @since 1.0.0-RC1
 */
@FunctionalInterface
public interface Callback<F extends Throwable> {

    /**
     * Создаёт экземпляр, во время выполнения которого ничего не происходит.
     *
     * @param <F> тип программного сбоя или неисправности, возникающей во время выполнения нового экземпляра.
     *
     * @return Новый экземпляр, во время выполнения которого ничего не происходит.
     *
     * @since 1.0.0-RC1
     */
    @Contract("-> new")
    static <F extends Throwable> @NonNull Callback<F> empty() {
        return () -> {};
    }

    /**
     * Создаёт экземпляр, во время выполнения которого бросается переданный в этот метод программный сбой или
     * неисправность.
     *
     * @param throwable программный сбой или неисправность.
     * @param <F> тип переданного программного сбоя или неисправности.
     *
     * @return Новый экземпляр, во время выполнения которого бросается переданный в этот метод программный сбой или
     * неисправность.
     *
     * @since 1.0.0-RC1
     */
    @Contract("!null -> new; null -> fail")
    static <F extends Throwable> @NonNull Callback<F> withThrowable(final @NonNull F throwable) throws ValidationFault {
        Validator.nonNull(throwable, "The passed throwable");
        return () -> {
            throw throwable;
        };
    }

    /**
     * Создаёт экземпляр, во время выполнения которого бросается переданный в этот метод программный сбой или
     * неисправность.
     *
     * @param supplier поставщик программный сбой или неисправность.
     * @param <F1> тип переданного программного сбоя или неисправности.
     *
     * @return Новый экземпляр, во время выполнения которого бросается переданный в этот метод программный сбой или
     * неисправность.
     *
     * @since 1.0.0-RC1
     */
    @Contract("!null -> new; null -> fail")
    static <F1 extends Throwable, F2 extends F1> @NonNull Callback<F1> withThrowable(
            final @NonNull Supplier<F1, F2> supplier) throws ValidationFault {
        Validator.nonNull(supplier, "The passed supplier");
        return () -> {
            throw supplier.supply();
        };
    }

    /**
     * Выполняет обратный вызов.
     *
     * @throws ValidationFault выполнение обратного вызова завершилось неудачно.
     * @throws F выполнение обратного вызова завершилось неудачно.
     * @since 1.0.0-RC1
     */
    @Contract("-> ?")
    void call() throws ValidationFault, F;

    /**
     * Соединяет эту и переданную функцию так, что во время выполнения обратного вызова сначала выполняется эта функция,
     * а потом, если программный сбой или неисправность не была сгенерирована, выполняется переданная функция.
     *
     * @param callback функция обратного вызова.
     * @param <PF> тип программного сбоя или неисправности, возникающей во время выполнения переданной функции.
     *
     * @return Новая функция обратного вызова, во время выполнения которой сначала выполняется эта функция, а потом,
     * если программный сбой или неисправность не была сгенерирована, выполняется переданная функция.
     *
     * @throws ValidationFault не допускается, чтобы переданная функция была нулевая.
     * @implSpec При выполнении новой функции обратного вызова должны соблюдаться следующие условия: <ol>
     * <li>
     * Переданная функция должна быть выполнена, только если программный сбой или неисправность не была сгенерирована во
     * время выполнения этой функции.
     * </li>
     * <li>
     * Если программный сбой или неисправность была сгенерирована во время выполнения этой или переданной функции, то
     * она обязательно должна быть брошена.
     * </li>
     * </ol>
     * @since 1.0.0-RC1
     */
    @Contract("!null -> new; null -> fault")
    default <PF extends F> @NonNull Callback<F> and(final @NonNull Callback<PF> callback) throws ValidationFault {
        Validator.nonNull(callback, "The passed callback");
        return () -> {
            call();
            callback.call();
        };
    }

    /**
     * Соединяет эту и переданную функцию так, что во время выполнения обратного вызова сначала выполняется эта функция,
     * а потом, если программный сбой или неисправность была сгенерирована, выполняется переданная функция.
     *
     * @param callback функция обратного вызова.
     * @param <PF> тип программного сбоя или неисправности, возникающей во время выполнения переданной функции.
     *
     * @return Новая функция обратного вызова, во время выполнения которой сначала выполняется эта функция, а потом,
     * если программный сбой или неисправность была сгенерирована, выполняется переданная функция.
     *
     * @throws ValidationFault не допускается, чтобы переданная функция была нулевая.
     * @implSpec При выполнении новой функции обратного вызова должны соблюдаться следующие условия: <ol>
     * <li>
     * Переданная функция должна быть выполнена, только если программный сбой или неисправность была сгенерирована при
     * выполнении этой функции.
     * </li>
     * <li>
     * Если программный сбой или неисправность была сгенерирована во время выполнения только этой функции, то она
     * обязательно должна быть брошена после выполнения переданной функции.
     * </li>
     * <li>
     * Если программный сбой или неисправность была сгенерирована во время выполнения как этой, так и переданной
     * функции, то первый, сгенерированный во время выполнения этой функции программный сбой или неисправность должен
     * быть прикреплён в роли подавленного ко второму, сгенерированному во время выполнения переданной функции
     * программному сбою или неисправности, которая впоследствии должна быть брошена.
     * </li>
     * </ol>
     * @since 1.0.0-RC1
     */
    @Contract("!null -> new; null -> fault")
    default <PF extends F> @NonNull Callback<F> or(final @NonNull Callback<PF> callback) throws ValidationFault {
        Validator.nonNull(callback, "The passed callback");
        return () -> {
            try {
                call();
            } catch (final @NonNull Throwable first) {
                try {
                    callback.call();
                } catch (final @NonNull Throwable second) {
                    second.addSuppressed(first);
                    throw second;
                }
                throw first;
            }
        };
    }

    /**
     * Соединяет эту и переданную функцию так, что во время выполнения обратного вызова сначала выполняется эта функция,
     * а потом — переданная функция.
     *
     * @param callback функция обратного вызова.
     * @param <PF> тип программного сбоя или неисправности, возникающей во время выполнения переданной функции.
     *
     * @return Новая функция обратного вызова, во время выполнения которой сначала выполняется эта функция, а потом —
     * переданная функция.
     *
     * @throws ValidationFault не допускается, чтобы переданная функция была нулевая.
     * @implSpec При выполнении новой функции обратного вызова должны соблюдаться следующие условия: <ol>
     * <li>
     * Если программный сбой или неисправность была сгенерирована во время выполнения этой функции, то переданная
     * функция всё равно должна быть выполнена.
     * </li>
     * <li>
     * Если программный сбой или неисправность была сгенерирована во время выполнения только этой функции, то она
     * обязательно должна быть брошена после выполнения переданной функции.
     * </li>
     * <li>
     * Если программный сбой или неисправность была сгенерирована во время выполнения только переданной функции, то она
     * обязательно должна быть брошена.
     * </li>
     * <li>
     * Если программный сбой или неисправность была сгенерирована во время выполнения как этой, так и переданной
     * функции, то первый, сгенерированный во время выполнения этой функции программный сбой или неисправность должен
     * быть прикреплён в роли подавленного ко второму, сгенерированному во время выполнения переданной функции
     * программному сбою или неисправности, которая впоследствии должна быть брошена.
     * </li>
     * </ol>
     * @since 1.0.0-RC1
     */
    @SuppressWarnings("unchecked")
    @Contract("!null -> new; null -> fault")
    default <PF extends F> @NonNull Callback<F> securedAnd(final @NonNull Callback<PF> callback) throws
                                                                                                 ValidationFault {
        Validator.nonNull(callback, "The passed callback");
        return () -> {
            @Nullable
            Throwable first = null;
            try {
                call();
            } catch (final @NonNull Throwable failure) {
                first = failure;
            }
            try {
                callback.call();
            } catch (final @NonNull Throwable second) {
                if (first != null) second.addSuppressed(first);
                throw second;
            }
            if (first != null) throw (F) first;
        };
    }

}
