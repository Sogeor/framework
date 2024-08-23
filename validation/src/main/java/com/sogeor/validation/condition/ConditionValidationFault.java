package com.sogeor.validation.condition;

import com.sogeor.validation.ValidationFault;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Представляет собой непроверяемую неисправность программы, возникающую из-за ошибки валидации условия.
 *
 * @since 1.0.0-RC1
 */
public class ConditionValidationFault extends ValidationFault {

    /**
     * Представляет собой сообщение по умолчанию.
     *
     * @since 1.0.0-RC1
     */
    public static final @NotNull String DEFAULT_MESSAGE = "Failed to validate the passed condition";

    /**
     * Представляет собой конструктор по умолчанию.
     *
     * @since 1.0.0-RC1
     */
    @Contract(pure = true)
    public ConditionValidationFault() {
        super(DEFAULT_MESSAGE, DEFAULT_CAUSE, DEFAULT_SUPPRESSION, DEFAULT_STACK_TRACE);
    }

    /**
     * Представляет собой конструктор, позволяющий задать сообщение.
     *
     * @param message сообщение.
     *
     * @since 1.0.0-RC1
     */
    @Contract(pure = true)
    public ConditionValidationFault(final @Nullable String message) {
        super(message, DEFAULT_CAUSE, DEFAULT_SUPPRESSION, DEFAULT_STACK_TRACE);
    }

    /**
     * Представляет собой конструктор, позволяющий задать причину возникновения.
     *
     * @param cause причина возникновения.
     *
     * @since 1.0.0-RC1
     */
    @Contract(pure = true)
    public ConditionValidationFault(final @Nullable Throwable cause) {
        super(DEFAULT_MESSAGE, cause, DEFAULT_SUPPRESSION, DEFAULT_STACK_TRACE);
    }

    /**
     * Представляет собой конструктор, позволяющий задать сообщение и причину возникновения.
     *
     * @param message сообщение.
     * @param cause причина возникновения.
     *
     * @since 1.0.0-RC1
     */
    @Contract(pure = true)
    public ConditionValidationFault(final @Nullable String message, final @Nullable Throwable cause) {
        super(message, cause, DEFAULT_SUPPRESSION, DEFAULT_STACK_TRACE);
    }

    /**
     * Представляет собой конструктор, позволяющий задать параметры подавления и трассировки стека.
     *
     * @param suppression параметр подавления.
     * @param stackTrace параметр трассировки стека.
     *
     * @since 1.0.0-RC1
     */
    @Contract(pure = true)
    public ConditionValidationFault(final boolean suppression, final boolean stackTrace) {
        super(DEFAULT_MESSAGE, DEFAULT_CAUSE, suppression, stackTrace);
    }

    /**
     * Представляет собой конструктор, позволяющий задать сообщение, причину возникновения, параметры подавления и
     * трассировки стека.
     *
     * @param message сообщение.
     * @param cause причина возникновения.
     * @param suppression параметр подавления.
     * @param stackTrace параметр трассировки стека.
     *
     * @since 1.0.0-RC1
     */
    @Contract(pure = true)
    public ConditionValidationFault(final @Nullable String message, final @Nullable Throwable cause,
                                    final boolean suppression, final boolean stackTrace) {
        super(message, cause, suppression, stackTrace);
    }

}
