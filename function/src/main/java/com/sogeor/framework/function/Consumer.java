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

package com.sogeor.framework.function;

import com.sogeor.framework.annotation.Contract;
import com.sogeor.framework.annotation.NonNull;
import com.sogeor.framework.annotation.Nullable;
import com.sogeor.framework.throwable.fault.ImaginaryFault;
import com.sogeor.framework.validation.ValidationFault;

/**
 * Представляет собой потребитель объекта.
 *
 * @param <T> тип объекта.
 * @param <F> тип программного сбоя или неисправности, возникающий при неудачном потреблении объекта.
 *
 * @since 1.0.0-RC1
 */
@FunctionalInterface
public interface Consumer<T, F extends Throwable> {

    /**
     * Создаёт экземпляр, который при потреблении объекта ничего не делает.
     *
     * @param <T> тип объекта, потребляемого новым экземпляром.
     *
     * @return Новый экземпляр, который при потреблении объекта ничего не делает.
     *
     * @since 1.0.0-RC1
     */
    @Contract("-> new")
    static <T> @NonNull Consumer<T, ImaginaryFault> empty() {
        return ignored -> {};
    }

    /**
     * Потребляет переданный объект.
     *
     * @param object объект.
     *
     * @throws ValidationFault неудачная валидация переданного объекта.
     * @throws F неудачное потребление переданного объекта.
     * @since 1.0.0-RC1
     */
    @Contract("? -> ?")
    void consume(final @Nullable T object) throws ValidationFault, F;

}
