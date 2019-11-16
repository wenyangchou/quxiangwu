package com.ruoyi.project.system.order.mapper;

import com.ruoyi.project.system.order.domain.Order;
import com.ruoyi.project.system.order.domain.UserBuyDTO;
import com.ruoyi.project.system.order.domain.UserSellDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMapper {

    int insertOrder(Order order);

    int updateOrder(Order order);

    int updateOrderByThingId(Order order);

    int deleteOrder(Long thingId);

    Order getById(Long orderId);

    List<UserSellDTO> getUserReleaseByStatus(@Param("userId") Long userId,@Param("status") Integer status);

    List<UserBuyDTO> getUserBuyByStatus(@Param("userId") Long userId,@Param("status") Integer status);
}
