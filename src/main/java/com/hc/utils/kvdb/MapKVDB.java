package com.hc.utils.kvdb;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.mapdb.BTreeMap;
import org.mapdb.DB;
import org.mapdb.Serializer;
import java.util.Iterator;
import java.util.Map;

/**
 * mapdb 检索
 */
public class MapKVDB implements IKVDB {
    private DB db;
    private BTreeMap<byte[],byte[]> map;
    public MapKVDB(DB db){
        Preconditions.checkNotNull(db);
        this.db = db;
        this.map = db.treeMap("db", Serializer.BYTE_ARRAY,Serializer.BYTE_ARRAY).createOrOpen();
    }
    @Override
    public void put(byte[] key, byte[] value)  {
        map.put(key,value);
    }

    @Override
    public byte[] get(byte[] key) {
        return map.get(key);
    }

    @Override
    public CloseableIterator<Pair<byte[], byte[]>> scan(byte[] start, byte[] end) {
        Iterator<Map.Entry<byte[],byte[]>> iterator = this.map.entryIterator(start,true,end,false);
        return new CloseableIterator<Pair<byte[], byte[]>>() {
            @Override
            public void close() {
                //do nothing
            }

            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public Pair<byte[], byte[]> next() {
                Map.Entry<byte[],byte[]> entry = iterator.next();
                Pair<byte[],byte[]> pair = new ImmutablePair<>(entry.getKey(),entry.getValue());
                return pair;
            }
        };
    }

    @Override
    public void close() {
        this.db.close();
    }
}
