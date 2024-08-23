/**
 * Представляет собой модуль для работы с программными сбоями и неисправностями.
 *
 * @see com.sogeor.throwable
 * @since 1.0.0-RC1
 */
module com.sogeor.throwable {
    requires org.jetbrains.annotations;

    exports com.sogeor.throwable.failure;
    exports com.sogeor.throwable.fault;
}