package com.ruoyi.project.system.order.service;

import java.math.BigDecimal;

public interface IOrderService {

    //拍下
    int addOrder(Long userId,Long thingId);

    int updatePayType(Long orderId,Integer payType);

    int updateQuxiangMoney(Long orderId, BigDecimal money);

    int updateStatus(Long orderId,Integer status);

    int updateComent(Long orderId,String comment);

    int updateScore(Long orderId,Integer score);
}
