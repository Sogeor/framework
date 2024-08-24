package com.sogeor.collection.writable;

import com.sogeor.collection.Collection;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * @since 1.0.0-RC1
 */
public interface WritableCollection<T> extends Collection<T> {

    /**
     * @since 1.0.0-RC1
     */
    @Contract(value = "-> !null", pure = true)
    @NotNull WritableIterator<T> iterator();

}
