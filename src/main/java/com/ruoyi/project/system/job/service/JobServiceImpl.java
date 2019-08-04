package com.ruoyi.project.system.job.service;

import com.ruoyi.project.system.job.domain.ServiceJob;
import com.ruoyi.project.system.job.mapper.ServiceJobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImpl implements IJobService {

    @Autowired
    private ServiceJobMapper serviceJobMapper;

    @Override
    public List<ServiceJob> getOnceByUserId(Long userId) {
        List<ServiceJob> serviceJobs = serviceJobMapper.getByUserIdOnce(userId);
        serviceJobs.forEach(serviceJob -> {
            if (serviceJob.getJobHistory()!=null){
                serviceJob.setStatus(0);
            }else{
                serviceJob.setStatus(1);
            }
        });
        return serviceJobs;
    }

    @Override
    public List<ServiceJob> getEveryDayByUserId(Long userId) {
        List<ServiceJob> serviceJobs = serviceJobMapper.getByUserIdEveryDay(userId);
        serviceJobs.forEach(serviceJob -> {
            if (serviceJob.getJobHistory()!=null){
                serviceJob.setStatus(0);
            }else{
                serviceJob.setStatus(1);
            }
        });
        return serviceJobs;
    }

    @Override
    public List<ServiceJob> getJobByUserId(Long userId) {
        List<ServiceJob> onceJobs = getOnceByUserId(userId);
        List<ServiceJob> everyDayJobs = getEveryDayByUserId(userId);
        onceJobs.addAll(everyDayJobs);
        return everyDayJobs;
    }
}
