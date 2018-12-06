package com.hc.utils.kvdb;

import jdk.internal.jline.internal.Preconditions;
import org.apache.commons.lang3.tuple.Pair;
import org.mapdb.BTreeMap;
import org.mapdb.DB;
import org.mapdb.Serializer;

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
        return null;
    }
}
