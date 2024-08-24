package com.sogeor.collection.inherited;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * @since 1.0.0-RC1
 */
public interface InheritedIterable<T> extends Iterable<T> {

    /**
     * @since 1.0.0-RC1
     */
    @Override
    @Contract(value = "-> !null", pure = true)
    @NotNull InheritedIterator<T> iterator();

}
