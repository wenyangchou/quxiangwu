package com.ruoyi.project.system.money.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class MoneyHistory {

    private Long id;

    private Date createTime;

    private Date modifyTime;

    private Long userId;

    private BigDecimal xiangquMoney;

    private String description;

    private String type;
}
