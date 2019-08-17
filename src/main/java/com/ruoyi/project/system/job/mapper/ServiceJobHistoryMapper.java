package com.ruoyi.project.system.job.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * author:zwy
 * Date:2019-08-17
 * Time:21:34
 */
@Repository
public interface ServiceJobHistoryMapper {

    int insert(@Param("userId") Long userId,@Param("jobId") Long jobId);

    Long getByDateAndUserIdAndJobId(@Param("date") Date date,@Param("userId") Long userId,@Param("jobId") Long jobId);

    Long getByUserIdAndJobId(@Param("userId") Long userId,@Param("jobId") Long jobId);
}
