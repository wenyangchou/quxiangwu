package com.ruoyi.project.system.order.service;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.system.order.domain.ConfirmTipDTO;
import com.ruoyi.project.system.order.domain.Order;
import com.ruoyi.project.system.order.mapper.OrderMapper;
import com.ruoyi.project.system.place.domain.UserPlace;
import com.ruoyi.project.system.place.mapper.UserPlaceMapper;
import com.ruoyi.project.system.thing.domain.Image;
import com.ruoyi.project.system.thing.domain.Thing;
import com.ruoyi.project.system.thing.mapper.ImageMapper;
import com.ruoyi.project.system.thing.mapper.ThingMapper;
import org.apache.commons.lang3.time.DateUtils;
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

    @Autowired
    private ImageMapper imageMapper;

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
    public Long addOrder(ConfirmTipDTO confirmTipDTO) {

        UserPlace userPlace = new UserPlace();
        userPlace.setProvince(confirmTipDTO.getProvince());
        userPlace.setCity(confirmTipDTO.getCity());
        userPlace.setCounty(confirmTipDTO.getCounty());
        userPlace.setPlace(confirmTipDTO.getDetailInfo());
        userPlace.setCellphone(confirmTipDTO.getTel());
        userPlace.setUserId(ShiroUtils.getUserId());
        userPlace.setDetail(confirmTipDTO.getDetailInfo());
        userPlace.setIsMaster(1);
        userPlace.setConsignee(confirmTipDTO.getConsignee());
        userPlaceMapper.insertUserPlace(userPlace);

        Order order = new Order();
        order.setUserId(ShiroUtils.getUserId());
        order.setThingId(confirmTipDTO.getSkuId());
        order.setStatus(0);
        order.setPlaceId(userPlace.getId());
        order.setPayType(0);
        order.setActualQuxiangMoney(confirmTipDTO.getSkuFinalPrice());
        order.setBeginTime(new Date());
        order.setEndTime(DateUtils.addDays(new Date(),7));
        order.setComment(confirmTipDTO.getMsg());
        order.setScore(0);
        orderMapper.insertOrder(order);

        return order.getId();
    }

    @Override
    public ConfirmTipDTO getConfirmTip(Long tipId) {

        Order order = orderMapper.getById(tipId);
        UserPlace userPlace = userPlaceMapper.getByPlaceId(order.getPlaceId());
        Thing thing = thingMapper.getById(order.getThingId());
        Image image = imageMapper.getById(thing.getTopImgId());
        ConfirmTipDTO confirmTipDTO = new ConfirmTipDTO();
        confirmTipDTO.setTipId(order.getId());
        confirmTipDTO.setStatus(order.getStatus());
        confirmTipDTO.setConsignee(userPlace.getConsignee());
        confirmTipDTO.setTel(userPlace.getCellphone());
        confirmTipDTO.setDetailInfo(userPlace.getDetail());
        confirmTipDTO.setSkuName(thing.getName());
        confirmTipDTO.setSkuImg(image.getImgPath()+"/"+image.getImageUrl());
        confirmTipDTO.setSkuPrice(thing.getPrice());
        confirmTipDTO.setSkuFinalPrice(order.getActualQuxiangMoney());
        confirmTipDTO.setPrice(thing.getPrice());
        confirmTipDTO.setTipTime(order.getCreateTime());

        return confirmTipDTO;
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
