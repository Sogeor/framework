package com.sogeor.validation.argument;

import com.sogeor.throwable.failure.utility.UtilityCreationFailure;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @since 1.0.0-RC1
 */
public final class ArgumentValidator {

    /**
     * @since 1.0.0-RC1
     */
    private ArgumentValidator() throws UtilityCreationFailure {
        throw new UtilityCreationFailure(
                UtilityCreationFailure.TEMPLATE_MESSAGE.formatted("the ArgumentValidator utility class"));
    }

    /**
     * @see #isNull(Object, String)
     * @since 1.0.0-RC1
     */
    @Contract(value = "null -> null; !null -> fail", pure = true)
    public static <T> @Nullable T isNull(final @Nullable T object) throws NonNullArgumentValidationFault {
        if (object == null) return null;
        throw new NonNullArgumentValidationFault();
    }

    /**
     * @see #isNull(Object)
     * @since 1.0.0-RC1
     */
    @Contract(value = "null, _ -> null; !null, _ -> fail", pure = true)
    public static <T> @Nullable T isNull(final @Nullable T object, final @Nullable String name) throws
                                                                                                NonNullArgumentValidationFault {
        if (object == null) return null;
        if (name == null) throw new NonNullArgumentValidationFault();
        throw new NonNullArgumentValidationFault(NonNullArgumentValidationFault.TEMPLATE_MESSAGE.formatted(name));
    }

    /**
     * @see #notNull(Object, String)
     * @since 1.0.0-RC1
     */
    @Contract(value = "!null -> param1; null -> fail", pure = true)
    public static <T> @NotNull T notNull(final @Nullable T object) throws NullArgumentValidationFault {
        if (object != null) return object;
        throw new NullArgumentValidationFault();
    }

    /**
     * @see #notNull(Object)
     * @since 1.0.0-RC1
     */
    @Contract(value = "!null, _ -> param1; null, _ -> fail", pure = true)
    public static <T> @NotNull T notNull(final @Nullable T object, final @Nullable String name) throws
                                                                                                NullArgumentValidationFault {
        if (object != null) return object;
        if (name == null) throw new NullArgumentValidationFault();
        throw new NullArgumentValidationFault(NullArgumentValidationFault.TEMPLATE_MESSAGE.formatted(name));
    }

}
