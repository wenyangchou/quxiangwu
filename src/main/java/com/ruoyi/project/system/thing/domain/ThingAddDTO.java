package com.ruoyi.project.system.thing.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * author:zwy
 * Date:2019-10-02
 * Time:23:38
 */
@Data
public class ThingAddDTO {

    private String name;

    private String desc;

    private List<String> imgList;

    private Long districtId;

    private Integer ifNew;

    private BigDecimal price;

    private Long typeId;

    private Integer tradeType;
}
