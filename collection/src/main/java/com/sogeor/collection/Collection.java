package com.sogeor.collection;

import com.sogeor.collection.inherited.InheritedIterable;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * @since 1.0.0-RC1
 */
public interface Collection<T> {

    /**
     * @since 1.0.0-RC1
     */
    @Contract(value = "-> !null", pure = true)
    @NotNull InheritedIterable<T> inherited();

}
