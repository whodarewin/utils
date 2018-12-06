package com.hc.utils.kvdb;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.hadoop.hbase.util.Bytes;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;
import org.rocksdb.RocksIterator;

public class RocksKVDB implements IKVDB {
    private RocksDB db;

    /**
     * @param db
     */
    public RocksKVDB(RocksDB db){
        Preconditions.checkNotNull(db);
        this.db = db;
    }


    @Override
    public void put(byte[] key, byte[] value) throws RocksDBException {
        this.db.put(key,value);
    }

    @Override
    public byte[] get(byte[] key) throws RocksDBException {
        return this.db.get(key);
    }

    @Override
    public CloseableIterator<Pair<byte[],byte[]>> scan(byte[] start, byte[] end) {
        RocksIterator iterator = this.db.newIterator();
        iterator.seek(start);
        return new CloseableIterator<Pair<byte[],byte[]>>() {
            @Override
            public void close() throws Exception {
                iterator.close();
            }

            @Override
            public boolean hasNext() {
                if(Bytes.compareTo(end,iterator.value()) < 0){
                    return false;
                }
                return iterator.isValid();
            }

            @Override
            public Pair<byte[],byte[]> next() {
                Pair pair = Pair.of(iterator.key(),iterator.value());
                iterator.next();
                return pair;
            }
        };
    }
}
