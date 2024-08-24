package com.sogeor.collection.immutable;

import com.sogeor.collection.readable.ReadableCollection;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * @since 1.0.0-RC1
 */
public interface ImmutableCollection<T> extends ReadableCollection<T> {

    /**
     * @since 1.0.0-RC1
     */
    @Override
    @Contract(value = "-> !null", pure = true)
    @NotNull ImmutableIterator<T> iterator();

}
