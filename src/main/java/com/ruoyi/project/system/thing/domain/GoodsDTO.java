package com.ruoyi.project.system.thing.domain;

import lombok.Data;

import java.math.BigDecimal;

/**
 * author:zwy
 * Date:2019-09-21
 * Time:21:22
 */
@Data
public class GoodsDTO {

    private String createBy;

    private String creatorAvatar;

    private String creatorArea;

    private Long id;

    private String surfaceImage;

    private String skuName;

    private BigDecimal price;

    private Integer ifCollect;
}
