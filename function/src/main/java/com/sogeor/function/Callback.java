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

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * @since 1.0.0-RC1
 */
@FunctionalInterface
public interface Callback<F extends Throwable> {

    /**
     * @since 1.0.0-RC1
     */
    @Contract(value = "-> new", pure = true)
    static <F extends Throwable> Callback<F> empty() {
        return () -> {};
    }

    /**
     * @since 1.0.0-RC1
     */
    void call() throws F;

    /**
     * @since 1.0.0-RC1
     */
    @Contract(value = "_ -> new", pure = true)
    default @NotNull Callback<F> merge(final @NotNull Callback<F> callback) {
        return () -> {
            call();
            callback.call();
        };
    }

    /**
     * @since 1.0.0-RC1
     */
    @Contract(value = "_ -> new", pure = true)
    default @NotNull Callback<F> mergeSafely(final @NotNull Callback<F> callback) {
        return () -> {
            try {
                call();
            } finally {
                callback.call();
            }
        };
    }

    /**
     * @since 1.0.0-RC1
     */
    @Contract(value = "_ -> new", pure = true)
    default @NotNull Callback<F> alternate(final @NotNull Callback<F> callback) {
        return () -> {
            try {
                call();
            } catch (final @NotNull Throwable ignored) {
                callback.call();
            }
        };
    }

}
