package com.ruoyi.project.system.order.service;

import com.ruoyi.project.system.order.domain.Order;
import com.ruoyi.project.system.order.mapper.OrderMapper;
import com.ruoyi.project.system.place.domain.UserPlace;
import com.ruoyi.project.system.place.mapper.UserPlaceMapper;
import com.ruoyi.project.system.thing.domain.Thing;
import com.ruoyi.project.system.thing.mapper.ThingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private UserPlaceMapper userPlaceMapper;

    @Autowired
    private ThingMapper thingMapper;

    @Override
    public int addOrder(Long userId, Long thingId) {

        UserPlace userPlace = userPlaceMapper.getMasterByUserId(userId);
        Thing thing = thingMapper.getById(thingId);

        Order order = new Order();
        order.setUserId(userId);
        order.setThingId(thingId);
        order.setStatus(0);
        order.setPlaceId(userPlace.getId());
        order.setPayType(0);
        order.setActualQuxiangMoney(thing.getPrice());
        order.setBeginTime(new Date());
        order.setEndTime(new Date());
        order.setComment("");
        order.setScore(0);
        return orderMapper.insertOrder(order);
    }

    @Override
    public int addOrder(Order order) {
        return orderMapper.insertOrder(order);
    }

    @Override
    public int updateOrder(Order order) {
        return orderMapper.updateOrder(order);
    }

    @Override
    public int updatePayType(Long orderId, Integer payType) {
        Order order = new Order();
        order.setId(orderId);
        order.setPayType(payType);
        return orderMapper.updateOrder(order);
    }

    @Override
    public int updateQuxiangMoney(Long orderId, BigDecimal money) {
        Order order = new Order();
        order.setId(orderId);
        order.setActualQuxiangMoney(money);
        return orderMapper.updateOrder(order);
    }

    @Override
    public int updateStatus(Long orderId, Integer status) {
        Order order = new Order();
        order.setId(orderId);
        order.setStatus(status);

        Thing thing = thingMapper.getById(orderMapper.getById(orderId).getThingId());

        if (status.equals(2) || status.equals(3)){
            thing.setStatus(2);
        }else if(status.equals(1)||status.equals(0)){
            thing.setStatus(0);
        }else{
            thing.setStatus(3);
        }

        thingMapper.updateThing(thing);
        return orderMapper.updateOrder(order);
    }

    @Override
    public int updateComent(Long orderId, String comment) {
        Order order = new Order();
        order.setId(orderId);
        order.setComment(comment);
        return orderMapper.updateOrder(order);
    }

    @Override
    public int updateScore(Long orderId, Integer score) {
        Order order = new Order();
        order.setId(orderId);
        order.setScore(score);
        return orderMapper.updateOrder(order);
    }
}
