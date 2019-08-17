package com.ruoyi.project.system.type.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * author:zwy
 * Date:2019-08-17
 * Time:21:06
 */
@Data
public class ServiceType extends BaseEntity {

    private Long id;

    private Date createTime;

    private Date modifyTime;

    private String name;
}
