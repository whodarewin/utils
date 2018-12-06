package com.hc.utils.kvdb;

import org.apache.commons.lang3.tuple.Pair;
import org.rocksdb.RocksDBException;

public class MapDBKVDB implements IKVDB {

    @Override
    public void put(byte[] key, byte[] value) throws RocksDBException {

    }

    @Override
    public byte[] get(byte[] key) throws RocksDBException {
        return new byte[0];
    }

    @Override
    public CloseableIterator<Pair<byte[], byte[]>> scan(byte[] start, byte[] end) {
        return null;
    }
}
