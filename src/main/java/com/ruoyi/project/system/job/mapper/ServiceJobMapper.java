package com.ruoyi.project.system.job.mapper;

import com.ruoyi.project.system.job.domain.ServiceJob;

import java.util.List;

public interface ServiceJobMapper {

    List<ServiceJob> getByUserIdOnce(Long userId);

    List<ServiceJob> getByUserIdEveryDay(Long userId);
}
