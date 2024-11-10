/*
 * Copyright 2024 Sogeor
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * Представляет собой модуль для работы с неизменяемыми, унаследованными и изменяемыми коллекциями, а также с
 * коллекциями только для чтения и только для записи.
 *
 * @see com.sogeor.collection
 * @since 1.0.0-RC1
 */
module com.sogeor.collection {
    requires com.sogeor.function;
    requires com.sogeor.throwable;
    requires com.sogeor.validation;
    requires org.jetbrains.annotations;

    exports com.sogeor.collection.immutable;
    exports com.sogeor.collection.inherited;
    exports com.sogeor.collection.mutable;
    exports com.sogeor.collection.readable;
    exports com.sogeor.collection.writable;
    exports com.sogeor.collection;
}