package com.ruoyi.project.system.thing.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * author:zwy
 * Date:2019-11-03
 * Time:19:23
 */
@Data
public class SkuDetailDTO {

    private List<String> img;

    private String name;

    private String desc;

    private BigDecimal price;

    private BigDecimal skuFinalPrice;

    private Boolean ifCollected;

    private Long userId;

    private String avatar;

    private String userName;

    private String preTime;

    private String area;
}
