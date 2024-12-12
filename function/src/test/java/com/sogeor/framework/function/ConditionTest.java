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

import com.sogeor.framework.annotation.NonNull;
import com.sogeor.framework.throwable.fault.ImaginaryFault;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.*;

final class ConditionTest {

    @Test
    void methodDirect() {
        final @NonNull var trueCondition = Condition.<ImaginaryFault>direct(true);
        assertNotNull(trueCondition);

        assertTrue(trueCondition.compute());

        final @NonNull var falseCondition = Condition.<ImaginaryFault>direct(false);
        assertNotNull(falseCondition);

        assertFalse(falseCondition.compute());
    }

    @Test
    void methodOf() {
        final @NonNull var data = new AtomicBoolean();

        final @NonNull var action = Condition.of(data::get);
        assertNotNull(action);

        assertEquals(data.get(), action.compute());
    }

    @Test
    void methodAnd() {
        final @NonNull var condition = Condition.<ImaginaryFault>direct(true).and(Condition.direct(true));
        assertNotNull(condition);

        assertTrue(condition.compute());
    }

    @Test
    void methodNand() {
        final @NonNull var condition = Condition.<ImaginaryFault>direct(false).nand(Condition.direct(false));
        assertNotNull(condition);

        assertTrue(condition.compute());
    }

    @Test
    void methodOr() {
        final @NonNull var condition = Condition.<ImaginaryFault>direct(false).or(Condition.direct(true));
        assertNotNull(condition);

        assertTrue(condition.compute());
    }

    @Test
    void methodNor() {
        final @NonNull var condition = Condition.<ImaginaryFault>direct(false).nor(Condition.direct(false));
        assertNotNull(condition);

        assertTrue(condition.compute());
    }

    @Test
    void methodXnor() {
        final @NonNull var condition = Condition.<ImaginaryFault>direct(true).xnor(Condition.direct(true));
        assertNotNull(condition);

        assertTrue(condition.compute());
    }

    @Test
    void methodXor() {
        final @NonNull var condition = Condition.<ImaginaryFault>direct(false).xor(Condition.direct(true));
        assertNotNull(condition);

        assertTrue(condition.compute());
    }

    @Test
    void methodImply() {
        final @NonNull var condition = Condition.<ImaginaryFault>direct(true).imply(Condition.direct(false));
        assertNotNull(condition);

        assertFalse(condition.compute());
    }

}
