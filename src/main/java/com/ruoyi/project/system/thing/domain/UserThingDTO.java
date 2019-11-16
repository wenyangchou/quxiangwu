package com.ruoyi.project.system.thing.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * author:zwy
 * Date:2019-11-16
 * Time:18:09
 */
@Data
public class UserThingDTO {

    private Date time;

    private Long skuId;

    private String skuName;

    private Integer collectionAmount;

    private Integer messageAmount;

    private BigDecimal price;

    private Long typeId;
}
