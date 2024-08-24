/**
 * Представляет собой модуль для работы с неизменяемыми, унаследованными и изменяемыми коллекциями, а также с
 * коллекциями только для чтения и только для записи.
 *
 * @see com.sogeor.collection
 * @since 1.0.0-RC1
 */
module com.sogeor.collection {
    requires org.jetbrains.annotations;
    requires com.sogeor.throwable;
    requires com.sogeor.validation;

    exports com.sogeor.collection;
    exports com.sogeor.collection.immutable;
    exports com.sogeor.collection.inherited;
    exports com.sogeor.collection.mutable;
    exports com.sogeor.collection.readable;
    exports com.sogeor.collection.writable;
}