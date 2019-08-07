package com.instaclustr.cassandra.backup.cli;

import static com.instaclustr.cassandra.backup.cli.BackupRestoreCLI.init;
import static com.instaclustr.picocli.CLIApplication.execute;

import com.google.inject.Inject;
import com.instaclustr.cassandra.backup.cli.BackupRestoreCLI.CLIJarManifestVersionProvider;
import com.instaclustr.cassandra.backup.impl.commitlog.BackupCommitLogsOperationRequest;
import com.instaclustr.picocli.CassandraJMXSpec;
import com.instaclustr.sidecar.operations.OperationsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine;

@CommandLine.Command(name = "commitlog-backup",
        mixinStandardHelpOptions = true,
        description = "Upload archived commit logs to remote storage.",
        sortOptions = false,
        versionProvider = CLIJarManifestVersionProvider.class
)
public class CommitLogBackupApplication implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(CommitLogBackupApplication.class);

    @CommandLine.Mixin
    private CassandraJMXSpec jmxSpec;

    @CommandLine.Mixin
    private BackupCommitLogsOperationRequest request;

    @Inject
    private OperationsService operationsService;

    public static void main(String[] args) {
        System.exit(execute(new CommitLogBackupApplication(), args));
    }

    @Override
    public void run() {
        init(this, jmxSpec, request, logger);
    }
}
