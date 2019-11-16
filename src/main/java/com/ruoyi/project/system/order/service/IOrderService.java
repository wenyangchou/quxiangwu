package com.ruoyi.project.system.order.service;

import com.ruoyi.project.system.order.domain.ConfirmTipDTO;
import com.ruoyi.project.system.order.domain.Order;
import com.ruoyi.project.system.order.domain.UserBuyDTO;
import com.ruoyi.project.system.order.domain.UserSellDTO;

import java.math.BigDecimal;
import java.util.List;

public interface IOrderService {

    //拍下
    int addOrder(Long userId,Long thingId);

    int addOrder(Order order);

    Long addOrder(ConfirmTipDTO confirmTipDTO);

    int updateOrder(Order order);

    ConfirmTipDTO getConfirmTip(Long tipId);

    int updatePayType(Long orderId,Integer payType);

    int updateQuxiangMoney(Long orderId, BigDecimal money);

    int updateStatus(Long orderId,Integer status);

    int updateStatusByThingId(Long thingId,Integer status);

    int updateComent(Long orderId,String comment);

    int updateScore(Long orderId,Integer score);

    int deleteOrder(Long thingId);

    List<UserSellDTO> getUserSelled();

    List<UserBuyDTO> getUserBuyed();
}
