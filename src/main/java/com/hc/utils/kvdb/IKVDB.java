package com.hc.utils.kvdb;

import org.apache.commons.lang3.tuple.Pair;
import org.rocksdb.RocksDBException;

import java.io.Closeable;

public interface IKVDB extends Closeable {
    void put(byte[] key, byte[] value) throws RocksDBException;
    byte[] get(byte[] key) throws RocksDBException;
    CloseableIterator<Pair<byte[],byte[]>> scan(byte[] start, byte[] end);
}
