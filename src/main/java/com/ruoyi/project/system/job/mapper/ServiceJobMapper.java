package com.ruoyi.project.system.job.mapper;

import com.ruoyi.project.system.job.domain.ServiceJob;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceJobMapper {

    List<ServiceJob> getByUserIdOnce(Long userId);

    List<ServiceJob> getByUserIdEveryDay(Long userId);

    ServiceJob getById(Long jobId);

}
