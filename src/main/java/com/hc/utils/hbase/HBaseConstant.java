package com.hc.utils.hbase;

import org.apache.hadoop.hbase.util.Bytes;

public interface HBaseConstant {
    String COLUMN_FAMILY = "column_family";
    byte[] COLUMN_FAMILY_BYTE = Bytes.toBytes("column_family");
}
