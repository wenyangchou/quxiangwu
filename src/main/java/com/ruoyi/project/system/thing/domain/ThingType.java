package com.ruoyi.project.system.thing.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * author:zwy
 * Date:2019-08-10
 * Time:14:39
 */
@Data
public class ThingType extends BaseEntity {

    private Long id;

    private Date createTime;

    private Date modifyTime;

    private String type;

}
