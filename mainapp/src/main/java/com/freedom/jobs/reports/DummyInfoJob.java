package com.freedom.jobs.reports;

import com.freedom.jobs.datasource.dataservice.JobsInfoDataService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DummyInfoJob extends AbstractJobs {

    private final JobsInfoDataService jobsInfoDataService;

    @Autowired
    public DummyInfoJob(JobsInfoDataService jobsInfoDataService) {
        this.jobsInfoDataService = jobsInfoDataService;
    }

    @Override
    public String getCronExpression() {
        return "0/30 0/1 * 1/1 * ? *";
    }

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        jobsInfoDataService.getAllJobs()
                .forEach(
                        jobsInfo -> log.info(jobsInfo.getName())
                );
    }
}
