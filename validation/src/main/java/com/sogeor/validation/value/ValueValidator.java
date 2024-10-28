package com.sogeor.validation.value;

import com.sogeor.throwable.failure.utility.UtilityCreationFailure;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @since 1.0.0-RC1
 */
public final class ValueValidator {

    /**
     * @since 1.0.0-RC1
     */
    private ValueValidator() throws UtilityCreationFailure {
        throw new UtilityCreationFailure(
                UtilityCreationFailure.TEMPLATE_MESSAGE.formatted("the ValueValidator utility class"));
    }

    /**
     * @see #isNull(Object, String)
     * @since 1.0.0-RC1
     */
    @Contract("null -> null; !null -> fail")
    public static <T> @Nullable T isNull(final @Nullable T object) throws NonNullValueValidationFault {
        if (object == null) return null;
        throw new NonNullValueValidationFault();
    }

    /**
     * @see #isNull(Object)
     * @since 1.0.0-RC1
     */
    @Contract("null, _ -> null; !null, _ -> fail")
    public static <T> @Nullable T isNull(final @Nullable T object, final @Nullable String name) throws
                                                                                                NonNullValueValidationFault {
        if (object == null) return null;
        if (name == null) throw new NonNullValueValidationFault();
        throw new NonNullValueValidationFault(NonNullValueValidationFault.TEMPLATE_MESSAGE.formatted(name));
    }

    /**
     * @see #notNull(Object, String)
     * @since 1.0.0-RC1
     */
    @Contract("!null -> param1; null -> fail")
    public static <T> @NotNull T notNull(final @Nullable T object) throws NullValueValidationFault {
        if (object != null) return object;
        throw new NullValueValidationFault();
    }

    /**
     * @see #notNull(Object)
     * @since 1.0.0-RC1
     */
    @Contract("!null, _ -> param1; null, _ -> fail")
    public static <T> @NotNull T notNull(final @Nullable T object, final @Nullable String name) throws
                                                                                                NullValueValidationFault {
        if (object != null) return object;
        if (name == null) throw new NullValueValidationFault();
        throw new NullValueValidationFault(NullValueValidationFault.TEMPLATE_MESSAGE.formatted(name));
    }

}
