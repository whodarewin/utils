package com.hc.utils.kvdb;

import org.apache.commons.lang3.tuple.Pair;

public interface Scanable {
    CloseableIterator<Pair<byte[],byte[]>> scan(byte[] start, byte[] end);
}
