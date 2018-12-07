package com.hc.utils.hbase;

import org.apache.hadoop.hbase.util.Bytes;

public interface HBaseConstant {
    char COLUMN_FAMILY = 'c';
    byte[] COLUMN_FAMILY_BYTE = Bytes.toBytes('c');
}
