package com.hc.utils.hbase;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;

public interface IObjConverter<K,V> {
    Put convertToPut(K k,V v);
    Pair<K,V> convertToT(Result r);
}
