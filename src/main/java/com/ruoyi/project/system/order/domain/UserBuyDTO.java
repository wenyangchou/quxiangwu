package com.ruoyi.project.system.order.domain;

import lombok.Data;

import java.math.BigDecimal;

/**
 * author:zwy
 * Date:2019-11-16
 * Time:20:21
 */
@Data
public class UserBuyDTO {

    private String sellerAvatar;

    private String sellerName;

    private Long sellerId;

    private Integer status;

    private Long skuId;

    private String skuImg;

    private String skuImgPrefix;

    private String skuName;

    private BigDecimal price;

    private Long typeId;

}
