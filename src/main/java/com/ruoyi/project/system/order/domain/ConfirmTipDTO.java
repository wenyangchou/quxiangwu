package com.ruoyi.project.system.order.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * author:zwy
 * Date:2019-10-19
 * Time:18:49
 */
@Data
public class ConfirmTipDTO {

    private Long tipId;

    private String consignee;

    private String tel;

    private String province;

    private String city;

    private String county;

    private String detailInfo;

    private Long skuId;

    private String msg;

    private Integer status;

    private BigDecimal skuFinalPrice;

    private BigDecimal price;

    private BigDecimal skuPrice;

    private String skuImg;

    private String skuName;

    private Date tipTime;
}
