package com.ruoyi.project.system.qualify.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * author:zwy
 * Date:2019-08-18
 * Time:01:29
 */
@Data
public class Qualify extends BaseEntity {

    private Long id;

    private Date createTime;

    private Date modifyTime;

    private Long userId;

    private String qualifyPositiveUrl;

    private String qualifyNegativeUrl;

    private Integer qualifyStatus;

    private Integer qualifyType;
}
