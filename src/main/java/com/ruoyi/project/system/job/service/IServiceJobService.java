package com.ruoyi.project.system.job.service;

import com.ruoyi.project.system.job.domain.ServiceJob;

import java.util.List;

public interface IServiceJobService {

    List<ServiceJob> getOnceByUserId(Long userId);

    List<ServiceJob> getEveryDayByUserId(Long userId);

    List<ServiceJob> getJobByUserId(Long userId);

    int signJob(Long jobId,Integer jobType);
}
