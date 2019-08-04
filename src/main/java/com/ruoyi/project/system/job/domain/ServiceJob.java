package com.ruoyi.project.system.job.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ServiceJob extends BaseEntity {

    private Long id;

    private Date createTime;

    private Date modifyTime;

    private String name;

    private BigDecimal quxiangMoney;

    private String description;

    private Integer type;

    private Date beginTime;

    private Date endTime;

    private Integer status; //0完成 1未完成

    private JobHistory jobHistory;
}
