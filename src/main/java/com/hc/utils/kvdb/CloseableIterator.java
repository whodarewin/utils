package com.hc.utils.kvdb;

import java.util.Iterator;

public interface CloseableIterator<T> extends Iterator<T> {
    void close() throws Exception;
}
