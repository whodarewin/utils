package com.hc.utils.procedure;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HConstants;
import org.apache.hadoop.hbase.procedure2.ProcedureExecutor;
import org.apache.hadoop.hbase.procedure2.store.LeaseRecovery;
import org.apache.hadoop.hbase.procedure2.store.ProcedureStore;
import org.apache.hadoop.hbase.procedure2.store.wal.WALProcedureStore;
import org.apache.hadoop.hbase.util.CommonFSUtils;

import java.io.IOException;

public class Test{
    private static final String HBASE_USE_FLUSH = "hbase.procedure.store.wal.use.hsync";
    public static void main(String[] args) throws IOException, InterruptedException {
        Configuration configuration = HBaseConfiguration.create();
        configuration.setBoolean(CommonFSUtils.UNSAFE_STREAM_CAPABILITY_ENFORCE,false);
        String walDir = "/Users/hancongcong/data/wal/wal";
        String archiveWalDir = "/Users/hancongcong/data/archiveWalDir";

        ProcedureStore store = new WALProcedureStore(configuration,
                new Path(walDir), new Path(archiveWalDir), new LeaseRecovery() {
            @Override
            public void recoverFileLease(FileSystem fs, Path path) throws IOException {

            }
        });
        store.start(1);
        ProcedureExecutor<Object> executor = new ProcedureExecutor<Object>(configuration,
                new Object(),store);
        executor.init(1,true);
        executor.startWorkers();
        executor.submitProcedure(new TestProcedure());
        Thread.sleep(1000000);
        executor.stop();

        store.stop(false);
    }
}