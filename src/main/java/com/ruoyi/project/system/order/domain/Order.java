package com.ruoyi.project.system.order.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import com.ruoyi.project.system.place.domain.UserPlace;
import com.ruoyi.project.system.thing.domain.Thing;
import com.ruoyi.project.system.user.domain.User;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Order extends BaseEntity {

    private Long id;

    private Date createTime;

    private Date modifyTime;

    private Long userId;

    private User user;

    private Long thingId;

    private Thing thing;

    private Integer status;

    private Long placeId;

    private UserPlace userPlace;

    private Integer payType;

    private BigDecimal actualQuxiangMoney;

    private Date beginTime;

    private Date endTime;

    private String comment;

    private Integer score;

}
