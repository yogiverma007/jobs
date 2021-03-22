package com.freedom.jobs.datasource.dataservice;

import com.freedom.jobs.datasource.model.JobsInfo;
import com.freedom.jobs.datasource.repository.JobsInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobsInfoDataService {

    private final JobsInfoRepository jobsInfoRepository;

    @Autowired
    public JobsInfoDataService(JobsInfoRepository jobsInfoRepository) {
        this.jobsInfoRepository = jobsInfoRepository;
    }

    public List<JobsInfo> getAllJobs() {
        return jobsInfoRepository.findAll();
    }

}