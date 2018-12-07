package com.hc.converter;

import org.apache.hadoop.hbase.util.Bytes;

public class StringConverter implements IConverter<String>{

    @Override
    public byte[] convert(String s) {
        if(s == null){
            return null;
        }
        return Bytes.toBytes(s);
    }

    @Override
    public String convert(byte[] bytes) {
        if(bytes == null){
            return null;
        }
        return Bytes.toString(bytes);
    }
}
