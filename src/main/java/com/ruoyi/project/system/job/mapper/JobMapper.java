package com.ruoyi.project.system.job.mapper;

import com.ruoyi.project.system.job.domain.Job;

import java.util.List;

public interface JobMapper {

    List<Job> getByUserIdOnce(Long userId);

    List<Job> getByUserIdEveryDay(Long userId);
}
