package com.sogeor.collection.mutable;

import com.sogeor.collection.readable.ReadableCollection;
import com.sogeor.collection.writable.WritableCollection;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * @since 1.0.0-RC1
 */
public interface MutableCollection<T> extends ReadableCollection<T>, WritableCollection<T> {

    /**
     * @since 1.0.0-RC1
     */
    @Override
    @Contract(value = "-> !null", pure = true)
    @NotNull MutableIterator<T> iterator();

}
