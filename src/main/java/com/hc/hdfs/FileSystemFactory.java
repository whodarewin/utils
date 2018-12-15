package com.hc.hdfs;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;

import java.io.IOException;

public class FileSystemFactory {
    public static FileSystem getFileSystem(String url) throws IOException {
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS", "hdfs://localhost:8020");
        FileSystem fs = FileSystem.get(configuration);
        return fs;
    }
}
