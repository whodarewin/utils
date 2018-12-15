package com.hc.utils.hbase;

import com.hc.utils.converter.IConverter;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellScanner;
import org.apache.hadoop.hbase.HConstants;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * todo:只做orm客户端，省略掉column family和column 这种属性
 * hbase 客户端
 */
public class HBaseClient<K,V> {

    private String zookeeperHost;
    private String zookeeperNodePath;
    private String tableName;
    private IConverter<K> keyConverter;
    private IConverter<V> valueConverter;
    private ThreadLocal<HTableInterface> htables = new ThreadLocal<HTableInterface>();
    private HConnection hConnection;
    private long ttl;

    public HBaseClient(String zookeeperHost, String zookeeperNodePath, String tableName,
                       IConverter<K> keyConverter, IConverter<V> valueConverter, long ttl) throws IOException {
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
    public void put(K key, Triple<byte[],byte[],V> value) throws IOException {
        byte[] k = keyConverter.convert(key);
        byte[] v = valueConverter.convert(value.getRight());
        Put put = new Put(k,ttl);
        put.addColumn(value.getLeft(),value.getMiddle(),v);
        getHTable().put(put);
    }

    public void put(K key, List<Triple<byte[],byte[],V>> values) throws IOException {
        for(Triple<byte[],byte[],V> triple : values){
            this.put(key,triple);
        }
    }

    /**
     * 读
     * @param key
     * @return
     * @throws IOException
     */
    public List<Triple<byte[],byte[],V>> get(K key) throws IOException {
        Result result = htables.get().get(new Get(keyConverter.convert(key)));
        CellScanner scanner  = result.cellScanner();
        List<Triple<byte[],byte[],V>> ret = new ArrayList<>();
        while(scanner.advance()){
            Cell cell = scanner.current();
            Triple triple = packageTriple(cell);
            ret.add(triple);
        }
        return ret;
    }

    private Triple<byte[],byte[],V> packageTriple(Cell cell){
        byte[] family = cell.getFamilyArray();
        byte[] column = cell.getQualifierArray();
        byte[] valueB = cell.getValueArray();
        V v = valueConverter.convert(valueB);
        Triple triple = new ImmutableTriple(family,column,v);
        return triple;
    }

    /**
     * 顺序检索
     * @param start
     * @param end
     * @return
     * @throws IOException
     */
    public List<Pair<K,List<Triple<byte[],byte[],V>>>> scan(String start, String end) throws IOException {
        Scan scan = new Scan();
        if(start != null){
            scan.setStartRow(Bytes.toBytes(start));
        }
        if(end != null){
            scan.setStopRow(Bytes.toBytes(end));
        }

        List<Pair<K, List<Triple<byte[], byte[], V>>>> ret = new ArrayList<>();
        try(ResultScanner scanner = getHTable().getScanner(scan)) {
            for (Result result : scanner) {
                CellScanner cellScanner = result.cellScanner();
                List<Triple<byte[], byte[], V>> triples = new ArrayList<>();
                while (cellScanner.advance()) {
                    Triple value = packageTriple(cellScanner.current());
                    triples.add(value);
                }
                ret.add(new ImmutablePair<>(keyConverter.convert(result.getRow()), triples));

            }
        }
        return ret;
    }

}
