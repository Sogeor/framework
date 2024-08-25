package com.sogeor.function;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * @since 1.0.0-RC1
 */
@FunctionalInterface
public interface Supplier<T, F extends Throwable> {

    /**
     * @since 1.0.0-RC1
     */
    @NotNull T get() throws F;

    /**
     * @since 1.0.0-RC1
     */
    @Contract(value = "_ -> new", pure = true)
    default Supplier<T, F> alternate(final Supplier<T, F> supplier) {
        return () -> {
            try {
                return get();
            } catch (final @NotNull Throwable ignored) {
                return supplier.get();
            }
        };
    }

}
