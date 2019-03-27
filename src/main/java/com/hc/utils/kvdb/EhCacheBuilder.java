package com.hc.utils.kvdb;

import org.ehcache.Cache;
import org.ehcache.PersistentCacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.MemoryUnit;

import java.io.File;

public class EhCacheBuilder implements IKVDBBuilder{

    private String path = "abc";

    @Override
    public IKVDB builder() {
        PersistentCacheManager persistentCacheManager = CacheManagerBuilder.newCacheManagerBuilder()
                .with(CacheManagerBuilder.persistence(new File(path, "data")))
                .withCache("data", CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class,
                        ResourcePoolsBuilder.newResourcePoolsBuilder().disk(10, MemoryUnit.MB, true))
                )
                .build(true);
        Cache<byte[],byte[]> cache = persistentCacheManager.getCache("data",byte[].class,byte[].class);
        return new EhCacheKVDB(cache);
    }
}
