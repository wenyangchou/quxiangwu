package com.ruoyi.project.system.job.domain;

import lombok.Data;

import java.math.BigDecimal;

/**
 * author:zwy
 * Date:2019-10-20
 * Time:16:00
 */
@Data
public class CoinDutyDTO {

    private String name;

    private String ifComplete;

    private BigDecimal amount;
}
