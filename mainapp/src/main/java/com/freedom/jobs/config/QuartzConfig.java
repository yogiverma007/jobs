package com.freedom.jobs.config;

import com.freedom.jobs.reports.AbstractJobs;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.List;

@Configuration
@Slf4j
public class QuartzConfig {

    private final Scheduler scheduler;
    private final List<AbstractJobs> abstractJobsList;

    @Autowired
    public QuartzConfig(Scheduler scheduler, List<AbstractJobs> abstractJobsList) {
        this.scheduler = scheduler;
        this.abstractJobsList = abstractJobsList;
    }

    @PostConstruct
    private void init() throws SchedulerException {
        log.info("initializing jobs");
        for (AbstractJobs abstractJobs : abstractJobsList) {
            if (abstractJobs.isActive()) {
                JobDetail jobDetail = createJobDetail(abstractJobs.getClass(), abstractJobs.getName());
                Trigger trigger = createTrigger(jobDetail, abstractJobs.getCronExpression(), abstractJobs.getName());

                scheduler.addJob(jobDetail, true);
                scheduler.scheduleJob(trigger);
            }
        }

        log.info("jobs initialized.");
    }

    private Trigger createTrigger(JobDetail jobDetail, String cronExpression, String name) {
        return TriggerBuilder
                .newTrigger()
                .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
                .forJob(jobDetail)
                .build();
    }

    private JobDetail createJobDetail(Class<? extends AbstractJobs> className, String name) {
        return JobBuilder
                .newJob(className)
                .withIdentity(className.getName())
                .storeDurably(true)
                .build();
    }

}