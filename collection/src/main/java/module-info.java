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
 * Предоставляет фундаментальные коллекции.
 *
 * @since 1.0.0-RC1
 */
module com.sogeor.framework.collection {
    requires com.sogeor.framework.annotation;
    requires com.sogeor.framework.common;
    requires com.sogeor.framework.function;
    requires com.sogeor.framework.throwable;
    requires com.sogeor.framework.validation;

    exports com.sogeor.framework.collection;
    exports com.sogeor.framework.collection.immutable;
    exports com.sogeor.framework.collection.inherited;
    exports com.sogeor.framework.collection.mutable;
    exports com.sogeor.framework.collection.readable;
    exports com.sogeor.framework.collection.writable;
}