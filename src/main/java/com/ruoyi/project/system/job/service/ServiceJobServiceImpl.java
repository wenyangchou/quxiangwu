package com.ruoyi.project.system.job.service;

import com.ruoyi.common.constant.JobType;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.system.job.domain.ServiceJob;
import com.ruoyi.project.system.job.mapper.ServiceJobHistoryMapper;
import com.ruoyi.project.system.job.mapper.ServiceJobMapper;
import com.ruoyi.project.system.user.domain.User;
import com.ruoyi.project.system.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class ServiceJobServiceImpl implements IServiceJobService {

    @Autowired
    private ServiceJobMapper serviceJobMapper;

    @Autowired
    private ServiceJobHistoryMapper serviceJobHistoryMapper;

    @Autowired
    private UserMapper userMapper;

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

    @Override
    public int signJob(Long jobId,Integer jobType) {

        Long userId = ShiroUtils.getUserId();
        Long getJobId;

        if (jobType.equals(JobType.ONCE)){
             getJobId = serviceJobHistoryMapper.getByUserIdAndJobId(userId,jobId);
            if (getJobId==null){
                return 0;
            }
        }else{
            getJobId = serviceJobHistoryMapper.getByDateAndUserIdAndJobId(new Date(),userId,jobId);
            if (getJobId==null){
                return 0;
            }
        }

        int result = serviceJobHistoryMapper.insert(userId,jobId);

        if (result>0){
            ServiceJob serviceJob = serviceJobMapper.getById(jobId);
            User user = ShiroUtils.getUser();
            user.setXianquMoney(user.getXianquMoney().add(serviceJob.getQuxiangMoney()));
            userMapper.updateUser(user);

        }

        return result;
    }
}
