package com.freedom.jobs.reports;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.quartz.QuartzJobBean;

@Slf4j
public abstract class AbstractJobs extends QuartzJobBean {

    public abstract String getCronExpression();

    public abstract String getName();

    public abstract boolean isActive();

}