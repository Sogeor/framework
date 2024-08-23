package com.sogeor.throwable.failure;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

/**
 * Представляет собой проверяемый сбой программы, то есть сбой, который должен быть перехвачен и обработан или передан
 * дальше.
 *
 * @apiNote При его обработке чаще всего следует завершать работу программы, потому что он подразумевает наличие
 * критических неисправностей, исправление или игнорирование которых может привести к неопределённому поведению
 * программы.
 * @see UncheckedFailure
 * @since 1.0.0-RC1
 */
public class CheckedFailure extends Throwable {

    /**
     * Представляет собой сообщение по умолчанию.
     *
     * @since 1.0.0-RC1
     */
    public static final @Nullable String DEFAULT_MESSAGE = null;

    /**
     * Представляет собой причину возникновения по умолчанию.
     *
     * @since 1.0.0-RC1
     */
    public static final @Nullable Throwable DEFAULT_CAUSE = null;

    /**
     * Представляет собой параметр подавления по умолчанию.
     *
     * @since 1.0.0-RC1
     */
    public static final boolean DEFAULT_SUPPRESSION = true;

    /**
     * Представляет собой параметр трассировки стека по умолчанию.
     *
     * @since 1.0.0-RC1
     */
    public static final boolean DEFAULT_STACK_TRACE = true;

    /**
     * Представляет собой конструктор по умолчанию.
     *
     * @since 1.0.0-RC1
     */
    @Contract(pure = true)
    public CheckedFailure() {
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
    public CheckedFailure(final @Nullable String message) {
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
    public CheckedFailure(final @Nullable Throwable cause) {
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
    public CheckedFailure(final @Nullable String message, final @Nullable Throwable cause) {
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
    public CheckedFailure(final boolean suppression, final boolean stackTrace) {
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
    public CheckedFailure(final @Nullable String message, final @Nullable Throwable cause, final boolean suppression,
                          final boolean stackTrace) {
        super(message, cause, suppression, stackTrace);
    }

}
