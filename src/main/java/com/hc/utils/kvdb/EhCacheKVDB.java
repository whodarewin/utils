package com.hc.utils.kvdb;

import com.google.common.base.Preconditions;
import org.ehcache.Cache;
import org.ehcache.UserManagedCache;
import org.rocksdb.RocksDBException;

import java.io.IOException;

public class EhCacheKVDB implements IKVDB {

    private Cache<byte[],byte[]> cache;

    public EhCacheKVDB(Cache<byte[], byte[]> cache) {
        Preconditions.checkNotNull(cache);
        this.cache = cache;
    }

    @Override
    public void put(byte[] key, byte[] value) throws RocksDBException {
        cache.put(key,value);
    }

    @Override
    public byte[] get(byte[] key) throws RocksDBException {
        return cache.get(key);
    }

    @Override
    public void close() throws IOException {
        if(cache instanceof UserManagedCache){
            ((UserManagedCache)cache).close();
        }
    }
}
