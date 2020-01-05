package com.ruoyi.project.system.thing.domain;

import lombok.Data;

import java.math.BigDecimal;

/**
 * author:zwy
 * Date:2019-10-02
 * Time:19:15
 */
@Data
public class ThingDTO {

    private String createBy;

    private Long creatorID;

    private String creatorAvatar;

    private String creatorArea;

    private Long id;

    private String surfaceImg;

    private String skuName;

    private BigDecimal price;

    private Boolean ifCollect;

    private Integer status;

}
