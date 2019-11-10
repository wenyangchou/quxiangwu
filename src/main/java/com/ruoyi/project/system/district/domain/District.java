package com.ruoyi.project.system.district.domain;

import lombok.Data;

import java.util.Date;

/**
 * author:zwy
 * Date:2019-11-10
 * Time:17:57
 */
@Data
public class District {

    private Long districtId;

    private Date districtICreateTime;

    private Date districtIModifyTime;

    private String districtName;

    private Long cityId;

    private Date cityCreateTime;

    private Date cityModifyTime;

    private String cityName;
}
