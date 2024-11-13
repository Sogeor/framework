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

package com.sogeor.function;

import com.sogeor.validation.ValidationFault;
import com.sogeor.validation.argument.ArgumentValidator;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Представляет собой функцию обратного вызова.
 *
 * @param <F> тип программного сбоя или неисправности, возникающей во время выполнения этой функции.
 *
 * @since 1.0.0-RC1
 */
@FunctionalInterface
public interface Callback<F extends Throwable> {

    /**
     * Создаёт новую функцию обратного вызова, во время выполнения которой ничего не происходит.
     *
     * @param <F> тип программного сбоя или неисправности, возникающей во время выполнения новой функции обратного
     * вызова.
     *
     * @return Новая функция обратного вызова, во время выполнения которой ничего не происходит.
     *
     * @since 1.0.0-RC1
     */
    @Contract(value = "-> new", pure = true)
    static <F extends Throwable> @NotNull Callback<F> empty() {
        return () -> {};
    }

    /**
     * Выполняет обратный вызов.
     *
     * @throws F программный сбой или неисправность, возникающая во время выполнения обратного вызова.
     * @since 1.0.0-RC1
     */
    void call() throws F;

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
    @Contract(value = "_ -> new", pure = true)
    default <PF extends F> @NotNull Callback<F> and(final @NotNull Callback<PF> callback) throws ValidationFault {
        ArgumentValidator.notNull(callback, "The passed callback");
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
    @Contract(value = "_ -> new", pure = true)
    default <PF extends F> @NotNull Callback<F> or(final @NotNull Callback<PF> callback) throws ValidationFault {
        ArgumentValidator.notNull(callback, "The passed callback");
        return () -> {
            try {
                call();
            } catch (final @NotNull Throwable first) {
                try {
                    callback.call();
                } catch (final @NotNull Throwable second) {
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
    @Contract(value = "_ -> new", pure = true)
    default <PF extends F> @NotNull Callback<F> securedAnd(final @NotNull Callback<PF> callback) throws
                                                                                                 ValidationFault {
        ArgumentValidator.notNull(callback, "The passed callback");
        return () -> {
            @Nullable Throwable first = null;
            try {
                call();
            } catch (final @NotNull Throwable failure) {
                first = failure;
            }
            try {
                callback.call();
            } catch (final @NotNull Throwable second) {
                if (first != null) second.addSuppressed(first);
                throw second;
            }
            if (first != null) throw (F) first;
        };
    }

}
