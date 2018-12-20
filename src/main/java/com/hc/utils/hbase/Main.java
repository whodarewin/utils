package com.hc.utils.hbase;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HConstants;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
        Configuration conf = HBaseConfiguration.create();
        conf.set(HConstants.ZOOKEEPER_QUORUM,"127.0.0.1");

        Connection connection = ConnectionFactory.createConnection(conf);

        HTable hTable = (HTable) connection.getTable(TableName.valueOf("TestTable"));
        Scan scan = new Scan();
        ResultScanner scanner = hTable.getScanner(scan);
        for(Result result : scanner){
            System.out.println(Bytes.toString(result.getRow()));
        }
    }
}
