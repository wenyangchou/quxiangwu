package com.ruoyi.project.system.order.domain;

import lombok.Data;

import java.math.BigDecimal;

/**
 * author:zwy
 * Date:2019-11-16
 * Time:19:49
 */
@Data
public class UserSellDTO {

    private String buyerAvatar;

    private String buyerName;

    private Long buyerId;

    private Integer status;

    private Long skuId;

    private String skuImg;

    private String skuImagePrefix;

    private String skuName;

    private BigDecimal price;

    private Long typeId;
}
