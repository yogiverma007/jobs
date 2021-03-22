package com.freedom.jobs.datasource.repository;

import com.freedom.jobs.datasource.model.JobsInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface JobsInfoRepository extends JpaRepository<JobsInfo, Long>, JpaSpecificationExecutor<JobsInfo> {

    List<JobsInfo> findByName(String name);

}