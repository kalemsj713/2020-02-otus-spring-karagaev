package ru.kalemsj713.otus.exercise.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.kalemsj713.otus.exercise.service.MigrationBatchService;


@RequiredArgsConstructor
@ShellComponent
public class BatchCommands {

    private final MigrationBatchService batchService;

    @ShellMethod(value = "start migrate data from h2:sql to awesome Mongo", key = {"start", "s"})
    public void startMigrate() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        batchService.launchJob();
    }
}
