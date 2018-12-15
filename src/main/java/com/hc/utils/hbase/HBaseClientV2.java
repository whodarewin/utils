package com.hc.utils.hbase;

import com.hc.utils.converter.IConverter;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HConstants;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HBaseClientV2<K,V> {
    private String zookeeperHost;
    private String zookeeperNodePath;
    private String tableName;
    private IConverter<K> keyConverter;
    private IConverter<V> valueConverter;
    private ThreadLocal<HTableInterface> htables = new ThreadLocal<HTableInterface>();
    private HConnection hConnection;
    private long ttl;
    private byte[] column;
    private byte[] columnFamily;

    public HBaseClientV2(String zookeeperHost, String zookeeperNodePath, String tableName,
                       IConverter<K> keyConverter, IConverter<V> valueConverter, char columnName,
                       char columnFamily,long ttl) throws IOException {
        this.zookeeperHost = zookeeperHost;
        this.zookeeperNodePath = zookeeperNodePath;
        this.tableName = tableName;
        this.keyConverter = keyConverter;
        this.valueConverter = valueConverter;
        Configuration configuration = new Configuration();
        configuration.set(HConstants.ZOOKEEPER_QUORUM,zookeeperHost);
        configuration.set(HConstants.ZOOKEEPER_ZNODE_PARENT,zookeeperNodePath);
        hConnection = HConnectionManager.createConnection(configuration);
        this.ttl = ttl;
        this.column = Bytes.toBytes(columnName);
        this.columnFamily = Bytes.toBytes(columnFamily);

    }

    private HTableInterface getHTable() throws IOException {
        //double check not needed
        HTableInterface hTableInterface = htables.get();
        if(hTableInterface == null){
            hTableInterface = hConnection.getTable(tableName);
            htables.set(hTableInterface);
        }
        return hTableInterface;

    }

    /**
     * 写
     * @param key
     * @param value
     * @throws IOException
     */
    public void put(K key, V value) throws IOException {
        byte[] k = keyConverter.convert(key);
        byte[] v = valueConverter.convert(value);
        Put put = new Put(k,ttl);
        put.addColumn(columnFamily,column,v);
        getHTable().put(put);
    }

    /**
     * 读
     * @param key
     * @return
     * @throws IOException
     */
    public V get(K key) throws IOException {
        Result result = htables.get().get(new Get(keyConverter.convert(key)));
        byte[] r = result.getValue(columnFamily,column);
        V v = valueConverter.convert(r);
        return v;
    }

    /**
     * 顺序检索
     * @param start
     * @param end
     * @return
     * @throws IOException
     */
    public List<Pair<K,V>> scan(String start, String end) throws IOException {
        Scan scan = new Scan();
        if(start != null){
            scan.setStartRow(Bytes.toBytes(start));
        }
        if(end != null){
            scan.setStopRow(Bytes.toBytes(end));
        }

        List<Pair<K,V>> values = new ArrayList<>();
        try(ResultScanner scanner = getHTable().getScanner(scan)) {
            for (Result result : scanner) {
                K k = keyConverter.convert(result.getRow());
                V v = valueConverter.convert(result.getValue(columnFamily,column));
                Pair<K,V> kvPair = ImmutablePair.of(k,v);
                values.add(kvPair);
            }
        }
        return values;
    }
}
