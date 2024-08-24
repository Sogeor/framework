package com.sogeor.collection;

import com.sogeor.collection.inherited.InheritedIterator;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * @since 1.0.0-RC1
 */
public interface Iterator<T> {

    /**
     * @since 1.0.0-RC1
     */
    @Contract(value = "-> !null", pure = true)
    @NotNull InheritedIterator<T> inherited();

}
