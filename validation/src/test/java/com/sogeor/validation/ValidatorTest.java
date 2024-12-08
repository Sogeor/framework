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

package com.sogeor.validation;

import com.sogeor.annotation.NonNull;
import com.sogeor.throwable.failure.utility.UtilityCreationFailure;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

final class ValidatorTest {

    @Test
    void defaultConstructor() {
        for (final var constructor : Validator.class.getConstructors()) {
            constructor.setAccessible(true);
            assertThrowsExactly(UtilityCreationFailure.class, constructor::newInstance);
        }
    }

    @Test
    @SuppressWarnings("DataFlowIssue")
    void isNullMethod() {
        assertDoesNotThrow(() -> Validator.isNull(null));
        assertThrowsExactly(NonNullValidationFault.class, () -> Validator.isNull(new Object()));
    }

    @Test
    @SuppressWarnings("DataFlowIssue")
    void isNullWithNameMethod() {
        assertDoesNotThrow(() -> Validator.isNull(null, null));
        assertDoesNotThrow(() -> Validator.isNull(null, "The test name"));
        assertThrowsExactly(NonNullValidationFault.class, () -> Validator.isNull(new Object(), null));
        assertThrowsExactly(NonNullValidationFault.class, () -> Validator.isNull(new Object(), "The test name"));
    }

    @Test
    @SuppressWarnings("DataFlowIssue")
    void nonNullMethod() {
        assertDoesNotThrow(() -> Validator.nonNull(new Object()));
        assertThrowsExactly(NullValidationFault.class, () -> Validator.nonNull(null));
    }

    @Test
    @SuppressWarnings("DataFlowIssue")
    void nonNullWithNameMethod() {
        assertDoesNotThrow(() -> Validator.nonNull(new Object(), null));
        assertDoesNotThrow(() -> Validator.nonNull(new Object(), "The test name"));
        assertThrowsExactly(NullValidationFault.class, () -> Validator.nonNull(null, null));
        assertThrowsExactly(NullValidationFault.class, () -> Validator.nonNull(null, "The test name"));
    }

    @Test
    @SuppressWarnings("DataFlowIssue")
    void isFalseMethod() {
        assertDoesNotThrow(() -> Validator.isFalse(false));
        assertThrowsExactly(TrueValidationFault.class, () -> Validator.isFalse(true));
    }

    @Test
    @SuppressWarnings("DataFlowIssue")
    void isFalseWithNameMethod() {
        assertDoesNotThrow(() -> Validator.isFalse(false, null));
        assertDoesNotThrow(() -> Validator.isFalse(false, "The test name"));
        assertThrowsExactly(TrueValidationFault.class, () -> Validator.isFalse(true, null));
        assertThrowsExactly(TrueValidationFault.class, () -> Validator.isFalse(true, "The test name"));
    }

    @Test
    @SuppressWarnings("DataFlowIssue")
    void isTrueMethod() {
        assertDoesNotThrow(() -> Validator.isTrue(true));
        assertThrowsExactly(FalseValidationFault.class, () -> Validator.isTrue(false));
    }

    @Test
    @SuppressWarnings("DataFlowIssue")
    void isTrueWithNameMethod() {
        assertDoesNotThrow(() -> Validator.isTrue(true, null));
        assertDoesNotThrow(() -> Validator.isTrue(true, "The test name"));
        assertThrowsExactly(FalseValidationFault.class, () -> Validator.isTrue(false, null));
        assertThrowsExactly(FalseValidationFault.class, () -> Validator.isTrue(false, "The test name"));
    }

    @Test
    void equalMethod() {
        final @NonNull var object = new Object();
        assertDoesNotThrow(() -> Validator.equal(null, null));
        assertDoesNotThrow(() -> Validator.equal(object, object));
        assertThrowsExactly(NonEqualValidationFault.class, () -> Validator.equal(null, object));
        assertThrowsExactly(NonEqualValidationFault.class, () -> Validator.equal(object, null));
        assertThrowsExactly(NonEqualValidationFault.class, () -> Validator.equal(object, new Object()));
    }

    @Test
    void equalWithNamesMethod() {
        final @NonNull var object = new Object();
        assertDoesNotThrow(() -> Validator.equal(null, null, null, null));
        assertDoesNotThrow(() -> Validator.equal(null, null, "The primary test name", "The secondary test name"));
        assertDoesNotThrow(() -> Validator.equal(object, object, null, null));
        assertDoesNotThrow(() -> Validator.equal(object, object, "The primary test name", "The secondary test name"));
        assertThrowsExactly(NonEqualValidationFault.class, () -> Validator.equal(null, object, null, null));
        assertThrowsExactly(NonEqualValidationFault.class,
                            () -> Validator.equal(null, object, "The primary test name", "The secondary test name"));
        assertThrowsExactly(NonEqualValidationFault.class, () -> Validator.equal(object, null, null, null));
        assertThrowsExactly(NonEqualValidationFault.class,
                            () -> Validator.equal(object, null, "The primary test name", "The secondary test name"));
        assertThrowsExactly(NonEqualValidationFault.class, () -> Validator.equal(object, new Object(), null, null));
        assertThrowsExactly(NonEqualValidationFault.class,
                            () -> Validator.equal(object, new Object(), "The primary test name",
                                                  "The secondary test name"));
    }

    @Test
    void nonEqualMethod() {
        final @NonNull var object = new Object();
        assertDoesNotThrow(() -> Validator.nonEqual(object, null));
        assertDoesNotThrow(() -> Validator.nonEqual(null, object));
        assertDoesNotThrow(() -> Validator.nonEqual(object, new Object()));
        assertThrowsExactly(EqualValidationFault.class, () -> Validator.nonEqual(null, null));
        assertThrowsExactly(EqualValidationFault.class, () -> Validator.nonEqual(object, object));
    }

    @Test
    void nonEqualWithNamesMethod() {
        final @NonNull var object = new Object();
        assertDoesNotThrow(() -> Validator.nonEqual(object, null, null, null));
        assertDoesNotThrow(() -> Validator.nonEqual(object, null, "The primary test name", "The secondary test name"));
        assertDoesNotThrow(() -> Validator.nonEqual(null, object, null, null));
        assertDoesNotThrow(() -> Validator.nonEqual(null, object, "The primary test name", "The secondary test name"));
        assertDoesNotThrow(() -> Validator.nonEqual(object, new Object(), null, null));
        assertDoesNotThrow(
                () -> Validator.nonEqual(object, new Object(), "The primary test name", "The secondary test name"));
        assertThrowsExactly(EqualValidationFault.class, () -> Validator.nonEqual(null, null, null, null));
        assertThrowsExactly(EqualValidationFault.class,
                            () -> Validator.nonEqual(null, null, "The primary test name", "The secondary test name"));
        assertThrowsExactly(EqualValidationFault.class, () -> Validator.nonEqual(object, object, null, null));
        assertThrowsExactly(EqualValidationFault.class,
                            () -> Validator.nonEqual(object, object, "The primary test name",
                                                     "The secondary test name"));
    }

}
