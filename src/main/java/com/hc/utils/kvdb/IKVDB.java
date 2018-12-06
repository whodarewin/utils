package com.hc.utils.kvdb;

import org.apache.commons.lang3.tuple.Pair;
import org.rocksdb.RocksDBException;


public interface IKVDB {
    void put(byte[] key, byte[] value) throws RocksDBException;
    byte[] get(byte[] key) throws RocksDBException;
    CloseableIterator<Pair<byte[],byte[]>> scan(byte[] start, byte[] end);
}
