package com.hc.utils.hbase;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HConstants;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Configuration conf = HBaseConfiguration.create();
        conf.set(HConstants.ZOOKEEPER_QUORUM,"127.0.0.1");

        Connection connection = ConnectionFactory.createConnection(conf);

        HTable hTable = (HTable) connection.getTable(TableName.valueOf("TestTable"));
        for(int i = 0;i < 100; i++){
            Scan scan = new Scan();
            scan.setAsyncPrefetch(true);
            ResultScanner scanner = hTable.getScanner(scan);
            scanner.next();
        }
        Thread.sleep(1000000);
    }
}
