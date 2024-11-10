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
 * Представляет собой модуль для валидации аргументов методов и конструкторов, истинности и ложности условий, значения
 * полей, анонимных значений, а также значений переменных.
 *
 * @see com.sogeor.validation
 * @since 1.0.0-RC1
 */
module com.sogeor.validation {
    requires com.sogeor.throwable;
    requires org.jetbrains.annotations;

    exports com.sogeor.validation;
    exports com.sogeor.validation.argument;
    exports com.sogeor.validation.condition;
    exports com.sogeor.validation.field;
    exports com.sogeor.validation.value;
    exports com.sogeor.validation.variable;
}