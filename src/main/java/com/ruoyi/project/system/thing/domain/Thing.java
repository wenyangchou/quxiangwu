package com.ruoyi.project.system.thing.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class Thing extends BaseEntity {

    private Long id;

    private Date createTime;

    private Date modifyTime;

    private Long topImgId;

    private String name;

    private BigDecimal price;

    private Integer priceType;

    private Long userId;

    private Long typeId;

    private Long districtId;

    private String description;

    private Integer status;

    private Image topImage;

    private List<Image> images;

    private Integer tradeType;

    private Integer isNew;
}
