package com.sogeor.collection.readable;

import com.sogeor.collection.Collection;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * @since 1.0.0-RC1
 */
public interface ReadableCollection<T> extends Collection<T> {

    /**
     * @since 1.0.0-RC1
     */
    @Contract(value = "-> !null", pure = true)
    @NotNull ReadableIterator<T> iterator();

}
