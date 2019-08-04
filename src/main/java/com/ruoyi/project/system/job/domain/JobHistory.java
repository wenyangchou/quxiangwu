package com.ruoyi.project.system.job.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
public class JobHistory extends BaseEntity {

    private Long id;

    private Date createTime;

    private Date modifyTime;

    private Long userId;

    private Long jobId;

}
