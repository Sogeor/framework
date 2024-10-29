package com.sogeor.function;

import com.sogeor.validation.argument.ArgumentValidator;
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
    @NotNull T supply() throws F;

    /**
     * @since 1.0.0-RC1
     */
    @Contract(value = "_ -> new", pure = true)
    default @NotNull Supplier<T, F> alternate(final @NotNull Supplier<T, F> supplier) {
        ArgumentValidator.notNull(supplier, "The passed supplier");
        return () -> {
            try {
                return supply();
            } catch (final @NotNull Throwable ignored) {
                return supplier.supply();
            }
        };
    }

}
