package com.hc.utils.hdfs;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;

import java.io.IOException;

public class FileSystemFactory {
    private static final String FS_DEFAULT_FS = "fs.defaultFS";

    public static FileSystem getFileSystem(String url) throws IOException {

        Configuration configuration = new Configuration();
        configuration.set(FS_DEFAULT_FS, url);
        FileSystem fs = FileSystem.get(configuration);
        return fs;

    }
}
