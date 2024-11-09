package com.sogeor.validation.argument;

import com.sogeor.throwable.failure.utility.UtilityCreationFailure;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Этот утилитарный класс представляет собой валидатор аргументов методов и конструкторов.
 *
 * @see #isNull(Object)
 * @see #isNull(Object, String)
 * @see #notNull(Object)
 * @see #notNull(Object, String)
 * @since 1.0.0-RC1
 */
public final class ArgumentValidator {

    /**
     * Генерирует проверяемый сбой.
     *
     * @throws UtilityCreationFailure не допускается создание хотя бы одного экземпляра утилитарного класса.
     * @apiNote Этот конструктор предназначен для предотвращения создания хотя бы одного экземпляра этого утилитарного
     * класса.
     * @since 1.0.0-RC1
     */
    private ArgumentValidator() throws UtilityCreationFailure {
        throw new UtilityCreationFailure(
                UtilityCreationFailure.TEMPLATE_MESSAGE.formatted("the ArgumentValidator utility class"));
    }

    /**
     * Валидирует объект и, если он нулевой, возвращает его, в противном случае генерирует непроверяемую неисправность.
     *
     * @param object объект.
     * @param <T> тип объекта.
     *
     * @throws NonNullArgumentValidationFault не допускается, чтобы объект был ненулевым.
     * @see #isNull(Object, String)
     * @since 1.0.0-RC1
     */
    @Contract("null -> null; !null -> fail")
    public static <T> @Nullable T isNull(final @Nullable T object) throws NonNullArgumentValidationFault {
        if (object == null) return null;
        throw new NonNullArgumentValidationFault();
    }

    /**
     * Валидирует объект и, если он нулевой, возвращает его, в противном случае генерирует непроверяемую неисправность с
     * шаблонным сообщением на основе имени объекта.
     *
     * @param object объект.
     * @param name имя объекта.
     * @param <T> тип объекта.
     *
     * @throws NonNullArgumentValidationFault не допускается, чтобы объект был ненулевым.
     * @see #isNull(Object)
     * @since 1.0.0-RC1
     */
    @Contract("null, _ -> null; !null, _ -> fail")
    public static <T> @Nullable T isNull(final @Nullable T object, final @Nullable String name) throws
                                                                                                NonNullArgumentValidationFault {
        if (object == null) return null;
        if (name == null) throw new NonNullArgumentValidationFault();
        throw new NonNullArgumentValidationFault(NonNullArgumentValidationFault.TEMPLATE_MESSAGE.formatted(name));
    }

    /**
     * Валидирует объект и, если он ненулевой, возвращает его, в противном случае генерирует непроверяемую
     * неисправность.
     *
     * @param object объект.
     * @param <T> тип объекта.
     *
     * @throws NullArgumentValidationFault не допускается, чтобы объект был нулевым.
     * @see #notNull(Object, String)
     * @since 1.0.0-RC1
     */
    @Contract("!null -> param1; null -> fail")
    public static <T> @NotNull T notNull(final @Nullable T object) throws NullArgumentValidationFault {
        if (object != null) return object;
        throw new NullArgumentValidationFault();
    }

    /**
     * Валидирует объект и, если он ненулевой, возвращает его, в противном случае генерирует непроверяемую неисправность
     * с шаблонным сообщением на основе имени объекта.
     *
     * @param object объект.
     * @param name имя объекта.
     * @param <T> тип объекта.
     *
     * @throws NullArgumentValidationFault не допускается, чтобы объект был нулевым.
     * @see #notNull(Object)
     * @since 1.0.0-RC1
     */
    @Contract("!null, _ -> param1; null, _ -> fail")
    public static <T> @NotNull T notNull(final @Nullable T object, final @Nullable String name) throws
                                                                                                NullArgumentValidationFault {
        if (object != null) return object;
        if (name == null) throw new NullArgumentValidationFault();
        throw new NullArgumentValidationFault(NullArgumentValidationFault.TEMPLATE_MESSAGE.formatted(name));
    }

}
