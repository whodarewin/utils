package com.hc.hdfs;


import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;

public class HDFS {
    public FileSystem system;

    public static HDFS init(String url) throws IOException {
        HDFS hdfs = new HDFS();
        hdfs.system = FileSystemFactory.getFileSystem(url);
        return hdfs;
    }


    public HDFS delete(String url) throws IOException {
        Path path = new Path(url);
        system.delete(path,true);
        return this;
    }

    public HDFS create(String url) throws IOException {
        Path path = new Path(url);
        system.create(path);
        return this;
    }


    public static void main(String[] args) throws IOException {
        HDFS.init("hdfs://localhost:8020").delete("hdfs://localhost:8020/hbase");
    }
}
