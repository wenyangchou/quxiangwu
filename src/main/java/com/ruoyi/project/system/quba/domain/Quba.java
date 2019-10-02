package com.ruoyi.project.system.quba.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * author:zwy
 * Date:2019-08-10
 * Time:15:23
 */
@Data
public class Quba extends BaseEntity {

    private Long id;

    private String logo;

    private Date createTime;

    private Date modifyTime;

    private String name;

    private Long ownerId;
}
