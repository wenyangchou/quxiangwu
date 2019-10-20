package com.ruoyi.project.system.job.service;

import com.ruoyi.common.constant.JobConstant;
import com.ruoyi.common.constant.MoneyConstant;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.monitor.job.domain.Job;
import com.ruoyi.project.system.job.domain.CoinDutyDTO;
import com.ruoyi.project.system.job.domain.ServiceJob;
import com.ruoyi.project.system.job.mapper.ServiceJobHistoryMapper;
import com.ruoyi.project.system.job.mapper.ServiceJobMapper;
import com.ruoyi.project.system.money.domain.MoneyHistory;
import com.ruoyi.project.system.money.mapper.MoneyHistoryMapper;
import com.ruoyi.project.system.user.domain.User;
import com.ruoyi.project.system.user.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class ServiceJobServiceImpl implements IServiceJobService {

    @Autowired
    private ServiceJobMapper serviceJobMapper;

    @Autowired
    private ServiceJobHistoryMapper serviceJobHistoryMapper;

    @Autowired
    private MoneyHistoryMapper moneyHistoryMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<ServiceJob> getOnceByUserId(Long userId) {
        List<ServiceJob> serviceJobs = serviceJobMapper.getByUserIdOnce(userId);
        serviceJobs.forEach(serviceJob -> {
            if (serviceJob.getJobHistory()!=null){
                serviceJob.setStatus(JobConstant.STATUS_FINISH);
            }else{
                serviceJob.setStatus(JobConstant.STATUS_UNFINISH);
            }
        });
        return serviceJobs;
    }

    @Override
    public List<ServiceJob> getEveryDayByUserId(Long userId) {
        List<ServiceJob> serviceJobs = serviceJobMapper.getByUserIdEveryDay(userId);
        serviceJobs.forEach(serviceJob -> {
            if (serviceJob.getJobHistory()!=null){
                serviceJob.setStatus(JobConstant.STATUS_FINISH);
            }else{
                serviceJob.setStatus(JobConstant.STATUS_UNFINISH);
            }
        });
        return serviceJobs;
    }

    @Override
    public List<ServiceJob> getJobByUserId(Long userId) {
        List<ServiceJob> onceJobs = getOnceByUserId(userId);
        List<ServiceJob> everyDayJobs = getEveryDayByUserId(userId);
        onceJobs.addAll(everyDayJobs);
        return onceJobs;
    }

    @Override
    public List<CoinDutyDTO> getCoinDuty() {
        List<ServiceJob> jobs = getJobByUserId(ShiroUtils.getUserId());

        List<CoinDutyDTO> coinDutyDTOS = new ArrayList<>();
        jobs.forEach(job->{
            CoinDutyDTO coinDutyDTO = new CoinDutyDTO();
            coinDutyDTO.setAmount(job.getQuxiangMoney());
            if (job.getStatus().equals(JobConstant.STATUS_FINISH)){
                coinDutyDTO.setIfComplete("完成");
            }else{
                coinDutyDTO.setIfComplete("未完成");
            }
            coinDutyDTO.setName(job.getName());
            coinDutyDTOS.add(coinDutyDTO);
        });

        return coinDutyDTOS;
    }

    @Override
    public int signJob(Long jobId,Integer jobType) {

        Long userId = ShiroUtils.getUserId();
        Long getJobId;

        if (jobType.equals(JobConstant.TIMES_ONCE)){
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

            MoneyHistory moneyHistory = new MoneyHistory();
            moneyHistory.setDescription(serviceJob.getDescription());
            moneyHistory.setType(MoneyConstant.TYPE_INCOME);
            moneyHistory.setUserId(userId);
            moneyHistory.setXiangquMoney(serviceJob.getQuxiangMoney());
            moneyHistoryMapper.addMoneyHistory(moneyHistory);

            userMapper.updateUser(user);

        }

        return result;
    }
}
