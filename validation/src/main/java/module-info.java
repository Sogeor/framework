/**
 * Представляет собой модуль для валидации аргументов, условий, полей и переменных.
 *
 * @see com.sogeor.validation
 * @since 1.0.0-RC1
 */
module com.sogeor.validation {
    requires org.jetbrains.annotations;
    requires com.sogeor.throwable;

    exports com.sogeor.validation;
    exports com.sogeor.validation.argument;
    exports com.sogeor.validation.condition;
    exports com.sogeor.validation.field;
    exports com.sogeor.validation.variable;
}