package com.sogeor.collection.mutable;

import com.sogeor.collection.readable.ReadableIterator;
import com.sogeor.collection.writable.WritableIterator;

/**
 * @since 1.0.0-RC1
 */
public interface MutableIterator<T> extends ReadableIterator<T>, WritableIterator<T> {}
