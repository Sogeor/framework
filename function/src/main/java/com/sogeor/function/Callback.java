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
